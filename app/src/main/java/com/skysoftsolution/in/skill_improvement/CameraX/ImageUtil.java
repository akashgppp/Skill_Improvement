package com.skysoftsolution.in.skill_improvement.CameraX;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.Log;
import android.util.Rational;
import android.util.Size;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.impl.ImageOutputConfig;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

@RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
public class ImageUtil {
    private static final String TAG = "ImageUtil";

    private ImageUtil() {
    }

    public static byte[] imageToJpegByteArray(ImageProxy image) throws EncodeFailedException {
        byte[] data = null;
        if (image.getFormat() == ImageFormat.JPEG) {
            data = jpegImageToJpegByteArray(image);
        } else if (image.getFormat() == ImageFormat.YUV_420_888) {
            data = yuvImageToJpegByteArray(image);
        } else {
            Log.w(TAG, "Unrecognized image format: " + image.getFormat());
        }
        return data;
    }

    public static byte[] cropByteArray(byte[] data, Rect cropRect) throws EncodeFailedException {
        if (cropRect == null) {
            return data;
        }

        Bitmap imageBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
        if (imageBitmap == null) {
            Log.w(TAG, "Source image for cropping can't be decoded.");
            return data;
        }

        Bitmap cropBitmap = cropBitmap(imageBitmap, cropRect);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        boolean success = cropBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        if (!success) {
            throw new EncodeFailedException("cropImage failed to encode jpeg.");
        }

        imageBitmap.recycle();
        cropBitmap.recycle();

        return out.toByteArray();
    }

    public static Bitmap cropBitmap(Bitmap bitmap, Rect cropRect) {
        if (cropRect.width() > bitmap.getWidth() || cropRect.height() > bitmap.getHeight()) {
            Log.w(TAG, "Crop rect size exceeds the source image.");
            return bitmap;
        }

        return Bitmap.createBitmap(
                bitmap, cropRect.left, cropRect.top, cropRect.width(), cropRect.height());
    }

    public static Bitmap flipBitmap(Bitmap bitmap, boolean flipHorizontal, boolean flipVertical) {
        if (!flipHorizontal && !flipVertical) {
            return bitmap;
        }

        Matrix matrix = new Matrix();
        if (flipHorizontal) {
            if (flipVertical) {
                matrix.preScale(-1.0f, -1.0f);
            } else {
                matrix.preScale(-1.0f, 1.0f);
            }
        } else if (flipVertical) {
            matrix.preScale(1.0f, -1.0f);
        }

        return Bitmap.createBitmap(
                bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int degree) {
        if (degree == 0) {
            return bitmap;
        }

        Matrix matrix = new Matrix();
        matrix.preRotate(degree);

        return Bitmap.createBitmap(
                bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static boolean isAspectRatioValid(Rational aspectRatio) {
        return aspectRatio != null && aspectRatio.floatValue() > 0 && !aspectRatio.isNaN();
    }

    public static boolean isAspectRatioValid(Size sourceSize, Rational aspectRatio) {
        return aspectRatio != null
                && aspectRatio.floatValue() > 0
                && isCropAspectRatioHasEffect(sourceSize, aspectRatio)
                && !aspectRatio.isNaN();
    }

    public static Rect computeCropRectFromAspectRatio(Size sourceSize, Rational aspectRatio) {
        if (!isAspectRatioValid(aspectRatio)) {
            Log.w(TAG, "Invalid view ratio.");
            return null;
        }

        int sourceWidth = sourceSize.getWidth();
        int sourceHeight = sourceSize.getHeight();
        float srcRatio = sourceWidth / (float) sourceHeight;
        int cropLeft = 0;
        int cropTop = 0;
        int outputWidth = sourceWidth;
        int outputHeight = sourceHeight;
        int numerator = aspectRatio.getNumerator();
        int denominator = aspectRatio.getDenominator();

        if (aspectRatio.floatValue() > srcRatio) {
            outputHeight = Math.round((sourceWidth / (float) numerator) * denominator);
            cropTop = (sourceHeight - outputHeight) / 2;
        } else {
            outputWidth = Math.round((sourceHeight / (float) denominator) * numerator);
            cropLeft = (sourceWidth - outputWidth) / 2;
        }

        return new Rect(cropLeft, cropTop, cropLeft + outputWidth, cropTop + outputHeight);
    }

    public static Rational rotate(
            Rational rational, @ImageOutputConfig.RotationValue int rotation) {
        if (rotation == 90 || rotation == 270) {
            return inverseRational(rational);
        }
        return rational;
    }

    private static byte[] nv21ToJpeg(byte[] nv21, int width, int height, @Nullable Rect cropRect)
            throws EncodeFailedException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        YuvImage yuv = new YuvImage(nv21, ImageFormat.NV21, width, height, null);
        boolean success = yuv.compressToJpeg(cropRect == null ? new Rect(0, 0, width, height) : cropRect, 100, out);
        if (!success) {
            throw new EncodeFailedException("YuvImage failed to encode jpeg.");
        }
        return out.toByteArray();
    }

    private static byte[] yuv_420_888toNv21(ImageProxy image) {
        ImageProxy.PlaneProxy yPlane = image.getPlanes()[0];
        ImageProxy.PlaneProxy uPlane = image.getPlanes()[1];
        ImageProxy.PlaneProxy vPlane = image.getPlanes()[2];

        ByteBuffer yBuffer = yPlane.getBuffer();
        ByteBuffer uBuffer = uPlane.getBuffer();
        ByteBuffer vBuffer = vPlane.getBuffer();
        yBuffer.rewind();
        uBuffer.rewind();
        vBuffer.rewind();

        int ySize = yBuffer.remaining();

        int position = 0;

        byte[] nv21 = new byte[ySize + (image.getWidth() * image.getHeight() / 2)];

        for (int row = 0; row < image.getHeight(); row++) {
            yBuffer.get(nv21, position, image.getWidth());
            position += image.getWidth();
            yBuffer.position(
                    Math.min(ySize, yBuffer.position() - image.getWidth() + yPlane.getRowStride()));
        }

        int chromaHeight = image.getHeight() / 2;
        int chromaWidth = image.getWidth() / 2;
        int vRowStride = vPlane.getRowStride();
        int uRowStride = uPlane.getRowStride();
        int vPixelStride = vPlane.getPixelStride();
        int uPixelStride = uPlane.getPixelStride();

        byte[] vLineBuffer = new byte[vRowStride];
        byte[] uLineBuffer = new byte[uRowStride];
        for (int row = 0; row < chromaHeight; row++) {
            vBuffer.get(vLineBuffer, 0, Math.min(vRowStride, vBuffer.remaining()));
            uBuffer.get(uLineBuffer, 0, Math.min(uRowStride, uBuffer.remaining()));
            int vLineBufferPosition = 0;
            int uLineBufferPosition = 0;
            for (int col = 0; col < chromaWidth; col++) {
                nv21[position++] = vLineBuffer[vLineBufferPosition];
                nv21[position++] = uLineBuffer[uLineBufferPosition];
                vLineBufferPosition += vPixelStride;
                uLineBufferPosition += uPixelStride;
            }
        }

        return nv21;
    }

    private static boolean isCropAspectRatioHasEffect(Size sourceSize, Rational aspectRatio) {
        int sourceWidth = sourceSize.getWidth();
        int sourceHeight = sourceSize.getHeight();
        int numerator = aspectRatio.getNumerator();
        int denominator = aspectRatio.getDenominator();

        return sourceHeight != Math.round((sourceWidth / (float) numerator) * denominator) || sourceWidth != Math.round((sourceHeight / (float) denominator) * numerator);
    }

    private static Rational inverseRational(Rational rational) {
        if (rational == null) {
            return rational;
        }
        return new Rational(rational.getDenominator(), rational.getNumerator());
    }

    private static boolean shouldCropImage(ImageProxy image) {
        Size sourceSize = new Size(image.getWidth(), image.getHeight());
        Size targetSize = new Size(image.getCropRect().width(), image.getCropRect().height());
        return !targetSize.equals(sourceSize);
    }

    private static byte[] jpegImageToJpegByteArray(ImageProxy image) throws EncodeFailedException {
        ImageProxy.PlaneProxy[] planes = image.getPlanes();
        ByteBuffer buffer = planes[0].getBuffer();
        byte[] data = new byte[buffer.capacity()];
        buffer.get(data);
        if (shouldCropImage(image)) {
            data = cropByteArray(data, image.getCropRect());
        }
        return data;
    }

    private static byte[] yuvImageToJpegByteArray(ImageProxy image) throws EncodeFailedException {
        return ImageUtil.nv21ToJpeg(ImageUtil.yuv_420_888toNv21(image), image.getWidth(), image.getHeight(), shouldCropImage(image) ? image.getCropRect() : null);
    }

    public static Bitmap cropAndScale(Bitmap source, int scale) {
        int factor = source.getHeight() <= source.getWidth() ? source.getHeight() : source.getWidth();
        int longer = source.getHeight() >= source.getWidth() ? source.getHeight() : source.getWidth();
        int x = source.getHeight() >= source.getWidth() ? 0 : (longer - factor) / 2;
        int y = source.getHeight() <= source.getWidth() ? 0 : (longer - factor) / 2;
        source = Bitmap.createBitmap(source, x, y, factor, factor);
        source = Bitmap.createScaledBitmap(source, scale, scale, false);
        return source;
    }

    public static final class EncodeFailedException extends Exception {
        EncodeFailedException(String message) {
            super(message);
        }
    }

}