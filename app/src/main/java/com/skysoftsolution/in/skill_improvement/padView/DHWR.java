package com.skysoftsolution.in.skill_improvement.padView;
import android.util.Log;
import java.util.ArrayList;

public class DHWR {
    static {
        System.loadLibrary("stlport_shared");
        System.loadLibrary("dhwr-base");
        System.loadLibrary("dhwr-core");
        System.loadLibrary("dhwr");
    }

    public final static String TAG = "DIOTEK";
    public final static String VERSION = "";
    public final static byte GESTURE_LTOR = 0x01;
    public final static byte GESTURE_RTOL = 0x02;
    public final static byte GESTURE_DELETE = 0x03;
    public final static byte GESTURE_BLACK = 0x04;
    public final static byte GESTURE_ENTER = 0x05;
    public final static byte GESTURE_MERGE = 0x06;
    public final static byte GESTURE_AWAY = 0x07;
    public final static byte GESTURE_INSERT = 0x08;


    private final static int BIT_FLAG(int n) {
        return (1 << n);
    }
    public final static int ERR_SUCCESS = 0x00000000;
    public final static int ERR_NORESULT = 0x00000001;
    public final static int ERR_NULL_POINTER = 0x00000002;
    public final static int ERR_OUTOFMEMORY = 0x00000003;
    public final static int ERR_OUTOFRANGE = 0x00000004;
    public final static int ERR_EMPTY_INK = 0x00000005;

    public final static int ERR_INVALID_ARGUMENTS = 0x00000006;
    //! 설정된 인식모델이 비정상적임
    public final static int ERR_INVALID_MODEL = 0x00000007;
    //! 잘못된 객체에 접근
    public final static int ERR_INVALID_INSTANCE = 0x00000008;
    //! 데모 기간/횟수 만료
    public final static int ERR_EXPIRE_DEMO = 0x00000009;
    //! 엔진이 실행 중
    public final static int ERR_ENGINE_BUSY = 0x0000000a;
    //! 인증 실패
    public final static int ERR_AUTHORIZATION_FAIL = 0x0000000b;
    //! 엔진 인스턴스가 이미 존재함
    public final static int ERR_ALREADY_EXIST = 0x0000000c;
    public final static int DLANG_ENGLISH = 0;
    //! 영어(영국)
    public final static int DLANG_ENGLISH_UK = 1;
    //! 영어(캐나다)
    public final static int DLANG_ENGLISH_CA = 2;
    //! 알바니아어
    public final static int DLANG_ALBANIAN = 3;
    //! 오스트리아어
    public final static int DLANG_AUSTRIA = 4;
    //! 바스크어(스페인)
    public final static int DLANG_BASQUE = 5;
    //! 카탈로니아어
    public final static int DLANG_CATALAN = 6;
    //! 크로아티아어
    public final static int DLANG_CROATIAN = 7;
    //! 체코어
    public final static int DLANG_CZECH = 8;
    //! 덴마크어
    public final static int DLANG_DANISH = 9;
    //! 네덜란드어(네덜란드)
    public final static int DLANG_DUTCH = 10;
    //! 에스토니아어
    public final static int DLANG_ESTONIAN = 11;
    //! 핀란드어
    public final static int DLANG_FINNISH = 12;
    //! 프랑스어
    public final static int DLANG_FRENCH = 13;
    //! 스페인어(스페인)
    public final static int DLANG_SPANISH = 14;
    //! 스페인어(맥시코)
    public final static int DLANG_SPANISH_MX = 15;
    //! 헝가리어
    public final static int DLANG_HUNGARIAN = 16;
    //! 아이슬란드어
    public final static int DLANG_ICELANDIC = 17;
    //! 이탈리아어
    public final static int DLANG_ITALIAN = 18;
    //! 라트비아어
    public final static int DLANG_LATVIAN = 19;
    //! 리투아니아어
    public final static int DLANG_LITHUANIAN = 20;
    //! 노르웨이어(보크말)
    public final static int DLANG_BOKMAL = 21;    // NORWEGIAN
    //! 폴란드어
    public final static int DLANG_POLISH = 22;
    //! 포르투갈어(포르투갈)
    public final static int DLANG_PORTUGUESE = 23;
    //! 포르투갈어(브라질)
    public final static int DLANG_PORTUGUESEB = 24;
    //! 루마니아어
    public final static int DLANG_ROMANIAN = 25;
    //! 슬로바키아어
    public final static int DLANG_SLOVAK = 26;
    //! 슬로베니아어
    public final static int DLANG_SLOVENIAN = 27;
    //! 스웨덴어
    public final static int DLANG_SWEDISH = 28;
    //! 터키어
    public final static int DLANG_TURKISH = 29;
    //! 베트남어
    public final static int DLANG_VIETNAMESE = 30;
    //! 벨로루시어
    public final static int DLANG_BELARUSIAN = 31;
    //! 불가리아어
    public final static int DLANG_BULGARIAN = 32;
    // Greek
    //! 그리스어
    public final static int DLANG_GREEK = 33;
    // Cyrillic
    //! 카자흐어
    public final static int DLANG_KAZAKH = 34;
    //! 마케도니아어
    public final static int DLANG_MACEDONIAN = 35;
    //! 몽골리아어
    public final static int DLANG_MONGOLIAN = 36;
    //! 러시아어
    public final static int DLANG_RUSSIAN = 37;
    //! 우크라이나어
    public final static int DLANG_UKRAINIAN = 38;
    // Latin, Cyrillic
    //! 세르비아어
    public final static int DLANG_SERBIAN = 39;
    //! 타타르어(러시아)
    public final static int DLANG_TATAR = 40;
    // Oceania
    //! 피지어(피지 공화국)
    public final static int DLANG_FIJIAN = 41;
    //! 마오리어(뉴질랜드)
    public final static int DLANG_MAORI = 42;
    //! 사모아어
    public final static int DLANG_SAMOAN = 43;
    //! 타히티어(프랑스령 타히티섬)
    public final static int DLANG_TAHITIAN = 44;
    //! 통가어(통가)
    public final static int DLANG_TONGAN = 45;
    // Africa
    //! 오로모어(케냐)
    public final static int DLANG_OROMO = 46;
    //! 소토어(남아프리카 공화국)
    public final static int DLANG_SOTHO = 47;
    //! 스와힐리어(케냐)
    public final static int DLANG_SWAHILI = 48;
    //! 스와트어(스와질란드)
    public final static int DLANG_SWATI = 49;
    //! 호사어(남아프리카 공화국)
    public final static int DLANG_XHOSA = 50;
    //! 줄루어(남아프리카)
    public final static int DLANG_ZULU = 51;

    //! 인도네시아어
    public final static int DLANG_INDONESIAN = 52;
    //! 말레이어
    public final static int DLANG_MALAY = 53;
    //! 갈리시아어(스페인)
    public final static int DLANG_GALICIAN = 54;
    //! 독일어(독일)
    public final static int DLANG_GERMANY = 55;
    //! 아일랜드어
    public final static int DLANG_IRISH = 56;
    //! 남아공 공용어(남아프리카)
    public final static int DLANG_AFRIKAANS = 57;
    //! 노르웨이어(노르웨이)
    public final static int DLANG_NYNORSK = 58;    // NORWEGIAN
    //! 네덜란드어(벨기에)
    public final static int DLANG_DUTCH_BE = 59;
    //! 아제르바이잔어
    public final static int DLANG_AZERBAIJANI = 60;

    // CJK
    //! 한국어
    public final static int DLANG_KOREAN = 101;
    //! 중국어(중국)
    public final static int DLANG_CHINA = 102;
    //! 중국어(대만)
    public final static int DLANG_TAIWAN = 103;
    //! 중국어(홍콩, 중국 특별행정구)
    public final static int DLANG_HONGKONG = 104;
    //! 일본어
    public final static int DLANG_JAPANESE = 105;
    // ARABIC
    //! 아랍어
    public final static int DLANG_ARABIC = 106;
    //! 페르시아어(이란)
    public final static int DLANG_FARSI = 107;
    //! 우르두어(파키스탄)
    public final static int DLANG_URDU = 108;
    // Devanagari
    //! 힌디어
    public final static int DLANG_HINDI = 109;
    //! 벵골어(방글라데시)
    public final static int DLANG_BENGALI = 110;
    // etc
    //! 히브리어(이스라엘)
    public final static int DLANG_HEBREW = 111;
    //! 태국어
    public final static int DLANG_THAI = 112;
    /*@}*/

    // --------- Language Type ---------//
    /**
     * @defgroup  LanguageType Language Type
     * @ingroup   PublicStaticAttributes
     * @brief     인식할 언어의 상세 설정을 위해서 사용할 값이 정의되어 있다.
     * @details     예를 들어, @ref LanguageMode "Language Mode"가 영어(#DLANG_ENGLISH)일 경우, 대문자만 인식하거나, 소문자만 인식하거나, 또는 숫자만 인식하게 하는 등의 설정이 가능하다.\n
     * - Example usage:
     * @code
     * // 영어 대문자만 인식
     * DHWR.AddLanguage(mSetting.GetHandle(), DHWR.DLANG_ENGLISH, DHWR.DTYPE_UPPERCASE);
     * // 영어 소문자만 인식
     * DHWR.AddLanguage(mSetting.GetHandle(), DHWR.DLANG_ENGLISH, DHWR.DTYPE_LOWERCASE);
     * // 영어 대문자, 소문자 동시 인식
     * DHWR.AddLanguage(mSetting.GetHandle(), DHWR.DLANG_ENGLISH, DHWR.DTYPE_UPPERCASE | DHWR.DTYPE_LOWERCASE);
     * // 숫자만 인식
     * DHWR.AddLanguage(mSetting.GetHandle(), DHWR.DLANG_ENGLISH, DHWR.DTYPE_NUMERIC);
     * // 기호만 인식
     * DHWR.AddLanguage(mSetting.GetHandle(), DHWR.DLANG_ENGLISH, DHWR.DTYPE_SIGN);
     *
     * // 한국어 자음만 인식
     * DHWR.AddLanguage(mSetting.GetHandle(), DHWR.DLANG_KOREAN, DHWR.DTYPE_CONSONANT);
     * // 한국어 모음만 인식
     * DHWR.AddLanguage(mSetting.GetHandle(), DHWR.DLANG_KOREAN, DHWR.DTYPE_VOWEL);
     * // 한국어 글자, 숫자 동시 인식
     * DHWR.AddLanguage(mSetting.GetHandle(), DHWR.DLANG_KOREAN, DHWR.DTYPE_KOREAN | DHWR.DTYPE_NUMERIC);
     * @endcode
     * - @ref LanguageType "Language Type"을 기호(#DTYPE_SIGN)로 설정할 경우, 인식 가능한 기호 목록은 다음과 같다.
    |Hex Code|Character|
    |------------|-----------|
    |0021 ~ 002F|! " # $ % & ' ( ) * + , - . /|
    |003A ~ 0040|: ; < = > ? @|
    |005B ~ 0060|[ \\ ] ^ _ `|
    |007B ~ 007E|{ \| } ~|
     */

    /*@{*/
    //! 타입 없음
    public final static int DTYPE_NONE        = BIT_FLAG(0);
    //! 대문자
    public final static int DTYPE_UPPERCASE   = BIT_FLAG(1);
    //! 소문자
    public final static int DTYPE_LOWERCASE   = BIT_FLAG(2);
    //! 자음
    public final static int DTYPE_CONSONANT   = BIT_FLAG(3);
    //! 모음
    public final static int DTYPE_VOWEL       = BIT_FLAG(4);
    //! 성조
    public final static int DTYPE_TONE        = BIT_FLAG(5);
    //! 숫자
    public final static int DTYPE_NUMERIC     = BIT_FLAG(6);
    //! 기호
    public final static int DTYPE_SIGN        = BIT_FLAG(7);
    // JAPANESE
    //! 히라가나
    public final static int DTYPE_HIRAGANA    = BIT_FLAG(8);
    //! 카타카나
    public final static int DTYPE_KATAKANA    = BIT_FLAG(9);
    //! 칸지
    public final static int DTYPE_KANJI       = BIT_FLAG(10);
    // CHINA, TAIWAN, HONGKONG
    //! 간체
    public final static int DTYPE_SIMP        = BIT_FLAG(11);
    //! 번체
    public final static int DTYPE_TRAD        = BIT_FLAG(12);
    //! 부수
    public final static int DTYPE_RADICAL     = BIT_FLAG(13);
    // SERBIAN, TATAR
    //! 라틴 문자
    public final static int DTYPE_LATIN       = BIT_FLAG(14);
    //! 키릴 문자
    public final static int DTYPE_CYRILLIC    = BIT_FLAG(15);
    // ARABIC, FARSI, URDU
    //! 아랍 문자
    public final static int DTYPE_ARABIC      = BIT_FLAG(16);
    // HEBREW
    //! 히브리 문자
    public final static int DTYPE_HEBREW      = BIT_FLAG(17);
    // BENGALI, HINDI
    //! 데바나가리 문자
    public final static int DTYPE_DEVANAGARI  = BIT_FLAG(18);
    // KOREAN
    //! 한글
    public final static int DTYPE_KOREAN      = BIT_FLAG(19);
    //! 한자
    public final static int DTYPE_HANJA       = BIT_FLAG(20);
    // gesture for editing
    //! 제스처
    public final static int DTYPE_GESTURE     = BIT_FLAG(21);
    /*@}*/

    // --------- Param type ---------//
    /*!
     *  @brief Param Type
     */
    public final static int DHWR_LOG_LEVEL = 0;
    public final static int DHWR_LOG_CALLBACK = 1;
    public final static int DHWR_WRITE_INK = 2;
    public final static int DHWR_LANG_MODEL = 3;

    // --------- log level ---------//
    /*!
     *  @brief Log Level
     */
    public final static int LEVEL_NONE = 0;
    public final static int LEVEL_ERROR = 1;
    public final static int LEVEL_WARN = 2;
    public final static int LEVEL_INFO = 3;
    public final static int LEVEL_DEBUG = 4;

    // --------- recognition mode ---------//
    /**
     * @defgroup  RecognitionMode Recognition Mode
     * @ingroup   PublicStaticAttributes
     * @brief     낱자로 쓰여진 글자를 인식할지, 여러 글자를 인식할지, 또는 여러 줄로 된 글자를 인식할지 설정하는데 사용된다.
     * @details     SetRecognitionMode()의 두번째 파라미터로 사용한다.
     */

    /*@{*/
    //! 낱자 인식
    public final static int SINGLECHAR = 0;
    //! 여러 글자 인식
    public final static int MULTICHAR = 1;
    //! 겹쳐 쓴 글자 인식
    public final static int OVERLAPCHAR = 2;
    //! 여러 줄로 된 글자 인식
    public final static int MULTILINE = 3;
    /*@}*/

    // --------- Ink Inner Class ---------//
    public static class Point {
        public int x = 0;
        public int y = 0;
    }

    public static class Ink {
        public Ink() {
            mId = CreateInkObject();
        }

        public Ink(long id) {
            mId = id;
        }

        @Override
        protected void finalize() throws Throwable {
            DHWR.DestroyInkObject(mId);
            super.finalize();
        }

        public boolean AddPoint(int x, int y) {
            return DHWR.AddPoint(mId, x, y);
        }

        public boolean EndStroke() {
            return DHWR.EndStroke(mId);
        }

        public void Clear() {
            DHWR.InkClear(mId);
        }

        public boolean GetPoint(int index, Point point) {
            return DHWR.GetInkPoint(mId, index, point);
        }

        public int GetSize() {
            return DHWR.GetInkCount(mId);
        }

        public long GetHandle() {
            return mId;
        }
        private long mId;
    }

    // --------- Setting Inner Class ---------//
    public static class Setting {
        public Setting() {
            mId = CreateSettingObject();
        }

        @Override
        protected void finalize() throws Throwable {
            DHWR.DestroySettingObject(mId);
            super.finalize();
        }

        public int SetMode(int mode) {
            return DHWR.SetRecognitionMode(mId, mode);
        }

        public int SetCandidateSize(int size) {
            return DHWR.SetCandidateSize(mId, size);
        }

        public int AddLanguage(int lang, int option) {
            return DHWR.AddLanguage(mId, lang, option);
        }

        public int GetLanguageSize() {
            return DHWR.GetLanguageSize(mId);
        }

        public int ClearLanguage() {
            return DHWR.ClearLanguage(mId);
        }

        public int SetUserCharSet(char[] charset) {
            return DHWR.SetUserCharSet(mId, charset);
        }

        public long GetHandle() {
            return mId;
        }

        private long mId;
    }

    // --------- Result Inner Class ---------//
    public static class BlockInfo {
        public int stroke_count;
        public int[] stroke_indices;
    }

    public static class Block {
        public ArrayList<String> candidates = new ArrayList<String>();
        public BlockInfo info;
    }

    public static class Line extends ArrayList<Block> {
        private static final long serialVersionUID = -3254237493961808528L;
    }

    public static class Result extends ArrayList<Line> {
        private static final long serialVersionUID = -318179353348774962L;
    }

    public DHWR() {}

    /***************************************************************
     * Main Raw APIs
     ***************************************************************/
    /**
     * @defgroup  Main Main APIs
     * @brief     엔진 사용에 필수적인 함수
     * @details     Create()를 호출하여 인식엔진을 생성한 후에 사용이 끝나면, 반드시 Close()를 호출하여 인식엔진을 소멸시켜야 한다.
     */

    /*@{*/
    /*!
     *  @brief 인식엔진 세션을 시작함
     *  @param [in] key License key 파일의 경로
     *  @return @ref ErrorCode "Error Code"
     */
    public final static native int Create(String key);

    /*!
     *  @brief 인식엔진 세션을 마침
     *  @return @ref ErrorCode "Error Code"
     */
    public final static native int Close();

    /*!
     *  @brief 인식모드 속성 값에 따라 입력 데이터를 인식함
     *  @param [in] ink 좌표가 입력된 Ink Object의 handle
     *  @param [out] result 인식된 결과가 저장된 결과 오브젝트의 handle
     *  @return @ref ErrorCode "Error Code"
     *  @see InkObject, ResultObject
     */
    public final static native int RecognizeWithContext(long ink, long result);

    /*!
     *  @brief 인식모드 속성 값에 따라 입력 데이터를 인식함\n
     *           RecognizeWithContext()와 동일한 기능을 하며, RecognizeWithContext()를 사용하기 쉽도록 wrapping한 함수이다.
     *  @param [in] ink 좌표가 입력된 Ink Object
     *  @param [out] result 인식된 결과가 저장된 결과 오브젝트
     *  @return @ref ErrorCode "Error Code"
     *  @see InkObject, ResultObject
     */
    public final static int Recognize(Ink ink, Result result) {
        result.clear();
        long handle_result = DHWR.CreateResultObject();
        int ret = DHWR.RecognizeWithContext(ink.GetHandle(), handle_result);
        long size_line = DHWR.GetLineSize(handle_result);
        for (int i = 0; i < size_line; i++) {
            Line line = new Line();
            long handle_line = DHWR.GetLine(handle_result, i);
            int size_block = DHWR.GetBlockSize(handle_line);
            for (int j = 0; j < size_block; j++) {
                Block block = new Block();
                long handle_block = DHWR.GetBlock(handle_line, j);
                BlockInfo info = new BlockInfo();
                int size_stroke = DHWR.GetStrokeSize(handle_block);
                info.stroke_count = size_stroke;
                info.stroke_indices = new int[size_stroke];
                DHWR.GetStrokeIndices(handle_block, info.stroke_indices, size_stroke);
                block.info = info;
                PrintLog(DHWR.LEVEL_DEBUG, TAG, "DHWR.java Stroke count : " + info.stroke_count);
                for (int k = 0; k < size_stroke; k++) {
                    PrintLog(DHWR.LEVEL_DEBUG, TAG, "DHWR.java Stroke ["+k+"] : " + info.stroke_indices[k]);
                }

                int size_candidates = DHWR.GetCandidateSize(handle_block);
                for (int k = 0; k < size_candidates; k++) {
                    String text = DHWR.GetCandidate(handle_block, k);
                    String strHex = "";
                    char a;
                    for (int l = 0; l < text.length(); l++) {
                        a = text.charAt(l);
                        strHex += "0x"+Integer.toHexString((int) (a & 0xFFFF));
                        if (l != text.length() - 1)
                            strHex += " ";
                    }
                    String stLog = text + "(" + strHex + ")";

                    PrintLog(DHWR.LEVEL_DEBUG, TAG, "DHWR.java [" + i + "," + j + "," + k + "]: \"" + stLog + "\"");
                    block.candidates.add(text);
                }
                if (block.candidates.isEmpty() == false) {
                    line.add(block);
                }
            }
            if (line.isEmpty() == false) {
                result.add(line);
            }
        }
        DHWR.DestroyResultObject(handle_result);
        return ret;
    }
    public final static native long CreateInkObject();

    /*!
     *  @brief 잉크 오브젝트를 제거한다
     *  @param [in] ink 잉크 오브젝트의 handle
     *  @return void
     */
    public final static native void DestroyInkObject(long ink);

    /*!
     *  @brief 인식엔진에서 인식할 터치 좌표(잉크 좌표)를 잉크 오브젝트에 추가함
     *  @param [in] ink 잉크 오브젝트의 handle
     *  @param [in] x x 좌표 값
     *  @param [in] y y 좌표 값
     *  @return 터치 좌표가 성공적으로 추가되면 true, 아니면 false를 반환한다
     */
    public final static native boolean AddPoint(long ink, int x, int y);

    /*!
     *  @brief AddPoint()를 호출하여 잉크 오브젝트에 터치 좌표 값을 추가하다가, Touch-Up 이벤트에 의해 한 획의 입력을 마치면 호출한다.
     *  @param [in] ink 잉크 오브젝트
     *  @return 한 획의 입력이 성공적으로 마쳤으면 true, 아니면 false를 반환한다
     */
    public final static native boolean EndStroke(long ink);

    /*!
     *  @brief 입력한 좌표 데이터를 초기화함
     *  @param [in] ink 잉크 오브젝트
     *  @return void
     */
    public final static native void InkClear(long ink);

    /*!
     *  @brief 입력된 잉크 index 값에 해당하는 잉크 좌표값을 받아옴
     *  @param [in] ink 잉크 오브젝트
     *  @param [in] index 받아오고자 하는 잉크의 index
     *  @param [out] point 해당 잉크의 x, y좌표 값
     *  @return 좌표값을 성공적으로 받아오면 true, 아니면 false를 반환한다
     */
    public final static native boolean GetInkPoint(long ink, int index, Point point);

    /*!
     *  @brief 현재까지 입력된 잉크의 카운트 값을 반환하다.\n
     *           잉크의 카운트 값이란, AddPoint()를 호출하여 추가된 (x, y) 좌표 값의 개수를 의미한다. AddPoint()를 호출한 횟수로 봐도 된다.
     *  @param [in] ink 잉크 오브젝트
     *  @return 잉크 카운트
     */
    public final static native int GetInkCount(long ink);
    /*@}*/

    /***************************************************************
     * Setting Raw APIs
     ***************************************************************/
    /**
     * @defgroup SettingObject SettingObject APIs
     * @ingroup  Main
     * @brief    엔진 동작을 결정하는 SettingObject관련 함수
     * @details     인식할 언어, 인식모드 등을 설정할 수 있는 API를 제공한다. SettingObject APIs는 일반적으로 다음과 같은 순서로 호출하게 된다.
     * -# 설정 오브젝트 생성 : CreateSettingObject()
     * -# 인식모드 설정 (낱자 인식/여러 글자 인식/여러 줄 인식) : SetRecognitionMode()
     * -# 인식할 언어 설정 : AddLanguage()
     * -# 최대 후보문자 개수 설정 : SetCandidateSize()
     * -# 설정 오브젝트를 인식에 사용하도록 설정 : SetAttribute()
     * -# 문자 인식 : Recognize()
     * -# 설정 오브젝트 제거 : DestroySettingObject()
     * @warning CreateSettingObject()로 생성된 오브젝트는 반드시 DestroySettingObject()를 호출하여 소멸시켜야 한다.
     */

    /*@{*/
    /*!
     *  @brief 인식모드 속성 값을 설정함
     *  @param [in] setting 설정이 완료된 Setting Object의 handle
     *  @return @ref ErrorCode "Error Code"
     *  @see SettingObject
     */
    public final static native int SetAttribute(long setting);

    /*!
     *  @brief 설정 오브젝트를 생성한다
     *  @return 설정 오브젝트의 handle
     */
    public final static native long CreateSettingObject();

    /*!
     *  @brief 설정 오브젝트를 제거한다
     *  @param [in] setting 설정 오브젝트의 handle
     *  @return void
     */
    public final static native void DestroySettingObject(long setting);

    /*!
     *  @brief 필기 인식 모드를 설정한다.(낱자 인식/여러 글자 인식/여러 줄 인식)
     *  @param [in] setting 설정 오브젝트의 handle
     *  @param [in] mode @ref RecognitionMode "인식 모드"
     *  @return @ref ErrorCode "Error Code"
     */
    public final static native int SetRecognitionMode(long setting, int mode);

    /*!
     *  @brief 인식 후보 출력 크기를 설정한다.
     *           10으로 설정할 경우, 후보가 최대 10개까지 생성될 수 있다는 의미이다. 반드시 10개가 되는 것은 아니다.
     *  @param [in] setting 설정 오브젝트의 handle
     *  @param [in] sizeCand 후보수
     *  @return @ref ErrorCode "Error Code"
     */
    public final static native int SetCandidateSize(long setting, int sizeCand);

    /*!
     *  @brief 인식할 언어를 추가 한다
     *  @param [in] setting 설정 오브젝트의 handle
     *  @param [in] lang 언어 값 (@ref LanguageMode "Language Mode")
     *  @param [in] option 언어에 따른 옵션 (@ref LanguageType "Language Type")
     *  @return @ref ErrorCode "Error Code"
     */
    public final static native int AddLanguage(long setting, int lang, int option);

    /*!
     *  @brief 설정된 언어 크기를 가져온다
     *  @param [in] setting 설정 오브젝트의 handle
     *  @return 언어 크기
     */
    public final static native int GetLanguageSize(long setting);

    /*!
     *  @brief 설정된 언어를 초기화 한다
     *  @param [in] setting 설정 오브젝트의 handle
     *  @return @ref ErrorCode "Error Code"
     */
    public final static native int ClearLanguage(long setting);

    /*!
     *  @brief 인식할 기호의 목록을 설정하면, 설정된 기호들만 인식하고, 그 외의 기호들은 무시할 수 있다.
     *  @param [in] setting 설정 오브젝트의 handle
     *  @param [in] charset 인식할 기호의 리스트\n
     *                            기호는 @ref LanguageType "Language Type" 챕터에서 표에 정의된 기호만 사용 가능하다.
     *  @return @ref ErrorCode "Error Code"
     */
    public final static native int SetUserCharSet(long setting, char[] charset);
    /*@}*/

    /***************************************************************
     * Result Raw APIs
     ***************************************************************/
    /**
     * @defgroup ResultObject ResultObject APIs
     * @ingroup  Main
     * @brief    인식된 결과를 저장하는 ResultObject 관련 함수
     * @details     CreateResultObject()로 생성된 오브젝트는 반드시 DestroyResultObject()를 호출하여 소멸시켜야 한다.
     * - 블럭 오브젝트 : 블럭 오브젝트는 문자열 리스트이다.
     * - 라인 오브젝트 : 라인 오브젝트는 블럭 오브젝트의 리스트이다.
     * - 결과 오브젝트 : 결과 오브젝트는 라인 오브젝트의 리스트이다.
     * - 각 오브젝트의 관계는 다음의 예시를 통해 확인할 수 있다.
     * -# @e "hello" 한 단어를 필기 했다면, 다음과 같이 결과 오브젝트에 인식 결과가 저장된다.\n
     * 사용자의 필기에 따라 후보 문자와 개수는 달라질 수 있다.
     * 1순위 후보가 best 후보가 된다.
     * @code
     * Result = { Line[0] }
     *     Line[0] = { Block[0] }
     *         Block[0] = { "hello", "hell", ... } // 1순위 후보: hello, 2순위 후보: hell, 3순위 후보: ...
     * @endcode
     * -# @e "hello world" 한 문장을 필기 했다면, 다음과 같이 결과 오브젝트에 인식 결과가 저장된다.
     * @code
     * Result = { Line[0] }
     *     Line[0] = { Block[0], Block[1] }
     *         Block[0] = { "hello", "hell", ... }
     *         Block[1] = { "world", "word", ... }
     * @endcode
     * -# @e "hello world" @e "good morning" 두 문장을 두 줄로 필기 했다면, 다음과 같이 결과 오브젝트에 인식 결과가 저장된다.
     * @code
     * Result = { Line[0], Line[1] }
     *     Line[0] = { Block[0], Block[1] }
     *         Block[0] = { "hello", "hell", ... }
     *         Block[1] = { "world", "word", ... }
     *     Line[1] = { Block[0], Block[1] }
     *         Block[0] = { "good", "good", ... }
     *         Block[1] = { "morning", "morming", ... }
     * @endcode
     */

    /*@{*/
    /*!
     *  @brief 결과 오브젝트를 생성한다
     *  @return 결과 오브젝트의 handle
     */
    public final static native long CreateResultObject();

    /*!
     *  @brief 결과 오브젝트 내의 라인 오브젝트 개수를 가져온다
     *  @param [in] context 결과 오브젝트의 handle
     *  @return 라인 오브젝트 개수
     */
    public final static native int GetLineSize(long context);

    /*!
     *  @brief 결과 오브젝트로부터 지정된 index의 라인 오브젝트 handle을 가져온다
     *  @param [in] context 결과 오브젝트의 handle
     *  @param [in] index 라인 오브젝트의 index
     *  @return 라인 오브젝트의 handle
     */
    public final static native long GetLine(long context, int index);

    /*!
     *  @brief 라인 오브젝트 내의 블럭 오브젝트 개수를 가져온다
     *  @param [in] line 라인 오브젝트의 handle
     *  @return 블럭 오브젝트 개수
     */
    public final static native int GetBlockSize(long line);

    /*!
     *  @brief 라인 오브젝트로부터 지정된 index의 블럭 오브젝트 handle을 가져온다
     *  @param [in] line 라인 오브젝트의 handle
     *  @param [in] index 블럭 오브젝트의 index
     *  @return 블럭 오브젝트의 handle
     */
    public final static native long GetBlock(long line, int index);

    /*!
     *  @brief 블럭 오브젝트 내 후보 문자열을 구성한 획의 개수를 얻어온다
     *  @param [in] block 블럭 오브젝트의 handle
     *  @return 획의 개수
     */
    public final static native int GetStrokeSize(long block);

    /*!
     *  @brief 블럭 오브젝트 내 후보 문자열을 구성한 획의 index들을 얻어온다
     *  @param [in] block 블럭 오브젝트의 handle
     *  @param [out] indices index Array
     *  @param [in] size index Array 사이즈
     *  @return @ref ErrorCode "Error Code"
     */
    public final static native int GetStrokeIndices(long block, int[] indices, int size);

    /*!
     *  @brief 블럭 오브젝트로부터 후보 크기를 가져온다.
     *  @param [in] block 블럭 오브젝트의 handle
     *  @return Candidate Size
     */
    public final static native int GetCandidateSize(long block);

    /*!
     *  @brief 블럭 오브젝트로부터 인식된 후보 문자열을 가져온다.
     *  @param [in] block 블럭 오브젝트의 handle
     *  @param [in] index 후보 index
     *  @return 인식 후보 문자열
     */
    public final static native String GetCandidate(long block, int index);

    /*!
     *  @brief 결과 오브젝트를 제거한다
     *  @param [in] context 결과 오브젝트의 handle
     *  @return void
     */
    public final static native void DestroyResultObject(long context);
    /*@}*/

    /***************************************************************
     * Optional APIs
     ***************************************************************/
    /**
     * @defgroup Optional Optional APIs
     */

    /*@{*/
    /*!
     *  @brief 엔진내부에서 사용하는 외부라이브러리의 경로 설정
     *  @warning Create()를 실행한 이후에 호출해야 한다
     *  @param [in] path 외부라이브러리의 경로
     *  @return @ref ErrorCode "Error Code"
     */
    public final static native int SetExternalLibraryPath(char[] path);

    /*!
     *  @brief 엔진내부에서 사용하는 외부리소스(언어모델 파일 등)의 경로 설정
     *  @warning Create()를 실행한 이후에 호출해야 한다
     *  @param [in] path 리소스 경로
     *  @return @ref ErrorCode "Error Code"
     *  @par &nbsp;&nbsp;&nbsp;example
     *  @code
     *  DHWR.SetExternalResourcePath("/data/data/package-name/hdb/".toCharArray());
     *  @endcode
     */
    public final static native int SetExternalResourcePath(char[] path);

    /*!
     *  @brief 특정 타입의 파라미터 값을 가져옴
     *  @param [in] type 파라미터 타입
     *  @param [out] param 특정 파리미터 타입의 값
     *  @return @ref ErrorCode "Error Code"
     */
    public final static native int SetParam(int type, byte[] param);

    /*!
     *  @brief 특정 타입의 파라미터 값을 설정함
     *  @param [in] type 파라미터 타입
     *  @param [in] param 특정 파리미터 타입의 값
     *  @return @ref ErrorCode "Error Code"
     */
    public final static native int GetParam(int type, byte[] param);

    /*!
     *  @brief 엔진의 빌드넘버를 가져옴
     *  @param [out] revision 엔진 빌드넘버
     *  @return @ref ErrorCode "Error Code"
     */
    public final static native int GetRevision(char[] revision);

    /*!
     *  @brief License key에 설정된 엔진의 due date를 가져온다
     *  @remarks License key의 due date가 존재하지 않을 경우 due date는 초기 값 0이다
     *  @param [out] dueDate License key의 due date, 년월일을 나타내는 8자리의 integer 값 (ex 20161206)
     *  @return @ref ErrorCode "Error Code"
     */
    public final static native int GetDueDate(int[] dueDate);

    /***************************************************************
     * Debug Helper functions
     ***************************************************************/
    /*!
     *  @brief 로그 출력을 활성화 한다
     *  @return @ref ErrorCode "Error Code"
     */
    public static int SetLogEnable() {
        byte[] level = new byte[8];
        level[0] = LEVEL_DEBUG;
        return SetParam(DHWR.DHWR_LOG_LEVEL, level);
    }

    /*!
     *  @brief 로그 출력을 비활성화 한다
     *  @return @ref ErrorCode "Error Code"
     */
    public static int SetLogDisable() {
        byte[] level = new byte[8];
        level[0] = LEVEL_NONE;
        return SetParam(DHWR.DHWR_LOG_LEVEL, level);
    }

    /*!
     *  @brief 로그 출력 레벨을 반환한다
     *  @return 로그 출력 레벨
     */
    public final static byte GetLogLevel() {
        byte[] level = new byte[8];
        DHWR.GetParam(DHWR.DHWR_LOG_LEVEL, level);

        return level[0];
    }

    /*!
     *  @brief 로그를 출력한다
     *  @param [in] logLevel 로그 레벨
     *  @param [in] tag TAG 문자열
     *  @param [in] log 출력할 로그 문자열
     *  @return void
     */
    public final static void PrintLog(int logLevel, String tag, String log) {
        if (logLevel <= GetLogLevel()) {
            switch(logLevel) {
                case DHWR.LEVEL_ERROR:
                    Log.e(tag, log);
                    break;
                case DHWR.LEVEL_DEBUG:
                    Log.d(tag, log);
                    break;
                case DHWR.LEVEL_INFO:
                    Log.i(tag, log);
                    break;
            };
        }
    }
    /*@}*/
}

