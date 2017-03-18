package com.ling.jibonetposa.entities;

import com.ling.jibonetposa.base.BaseEntity;

/**
 * Created by cuiqiang on 2017/3/16.
 */

public class TNLUEntity extends BaseEntity {

    private String answer;
    private String domain;//basic:music:weather:time:date:news:volume:joke:express:move:
    private String code;
    private Param param;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Param getParam() {
        return param;
    }

    public void setParam(Param param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "NLUResultEntity{" +
                "answer='" + answer + '\'' +
                ", domain='" + domain + '\'' +
                ", code='" + code + '\'' +
                ", param=" + param +
                '}';
    }

    public static class Param {
        private String action;
        private String artist;
        private String song;
        private String style;
        private Detail detail;

        @Override
        public String toString() {
            return "Param{" +
                    "action='" + action + '\'' +
                    ", artist='" + artist + '\'' +
                    ", song='" + song + '\'' +
                    ", style='" + style + '\'' +
                    ", detail=" + detail +
                    '}';
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public String getSong() {
            return song;
        }

        public void setSong(String song) {
            this.song = song;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }

        public Detail getDetail() {
            return detail;
        }

        public void setDetail(Detail detail) {
            this.detail = detail;
        }
    }

    public static class Detail {
        private Condition condition;

        public Condition getCondition() {
            return condition;
        }

        public void setCondition(Condition condition) {
            this.condition = condition;
        }

        @Override
        public String toString() {
            return "Detail{" +
                    "condition=" + condition +
                    '}';
        }
    }

    public static class Condition {
        private String windDir;
        private String windLevel;
        private String condition;
        private String humidity;

        @Override
        public String toString() {
            return "Condition{" +
                    "windDir='" + windDir + '\'' +
                    ", windLevel='" + windLevel + '\'' +
                    ", condition='" + condition + '\'' +
                    ", humidity='" + humidity + '\'' +
                    '}';
        }

        public String getWindDir() {
            return windDir;
        }

        public void setWindDir(String windDir) {
            this.windDir = windDir;
        }

        public String getWindLevel() {
            return windLevel;
        }

        public void setWindLevel(String windLevel) {
            this.windLevel = windLevel;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }
    }

}


