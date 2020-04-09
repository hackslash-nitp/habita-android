package club.hackslash.habita;

public class GardenItem {
    private int mImageResource;
    private String mText;
    private String mText2;

    public GardenItem(int ImageResource, String text1, String text2) {
        mImageResource=ImageResource;
        mText=text1;
        mText2=text2;
    }

    public String getmText() {
        return mText;
    }


    public int getmImageResource() {
        return mImageResource;
    }

    public String getmText2() {
        return mText2;
    }
}
