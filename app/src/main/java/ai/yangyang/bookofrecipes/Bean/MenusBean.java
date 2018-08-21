package ai.yangyang.bookofrecipes.Bean;

import java.util.List;

public class MenusBean {
    private String result;
    private String msg;
    private List<Menus> data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Menus> getData() {
        return data;
    }

    public void setData(List<Menus> data) {
        this.data = data;
    }
}
