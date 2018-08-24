package ai.yangyang.bookofrecipes.Util;

import android.annotation.SuppressLint;
import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

@SuppressLint("ParcelCreator")
public class ColorSuggestion implements SearchSuggestion {

    private String mColorName;
    private boolean mIsHistory = false;

    public ColorSuggestion(String suggestion){
        this.mColorName = suggestion.toLowerCase();
    }

    public ColorSuggestion(Parcel source) {
        this.mColorName = source.readString();
        this.mIsHistory = source.readInt() != 0;
    }

    public String getmColorName() {
        return mColorName;
    }

    public void setmColorName(String mColorName) {
        this.mColorName = mColorName;
    }

    public boolean ismIsHistory() {
        return mIsHistory;
    }

    public void setmIsHistory(boolean mIsHistory) {
        this.mIsHistory = mIsHistory;
    }

    @Override
    public String getBody() {
        return mColorName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mColorName);
        parcel.writeInt(mIsHistory ? 1 : 0);
    }


    public static final Creator<ColorSuggestion> CREATOR = new Creator<ColorSuggestion>() {
        @Override
        public ColorSuggestion createFromParcel(Parcel in) {
            return new ColorSuggestion(in);
        }

        @Override
        public ColorSuggestion[] newArray(int size) {
            return new ColorSuggestion[size];
        }
    };

}
