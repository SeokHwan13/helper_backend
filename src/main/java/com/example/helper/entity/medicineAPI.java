package com.example.helper.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class medicineAPI {
    private Header header;
    private Body body;

    @Getter
    @Setter
    public static class Header {
        @JsonProperty("resultCode")
        private String resultCode;

        @JsonProperty("resultMsg")
        private String resultMsg;
    }

    @Getter
    @Setter
    public static class Body {
        @JsonProperty("pageNo")
        private int pageNo;

        @JsonProperty("totalCount")
        private int totalCount;

        @JsonProperty("numOfRows")
        private int numOfRows;

        @JsonProperty("items")
        private List<Item> items;

        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true) // 정의되지 않은 필드는 무시
        public static class Item {
            @JsonProperty("entpName")
            private String entpName;

            @JsonProperty("itemName")
            private String itemName;

            @JsonProperty("itemSeq")
            private String itemSeq;

            @JsonProperty("efcyQesitm")
            private String efcyQesitm;

            @JsonProperty("useMethodQesitm")
            private String useMethodQesitm;

            @JsonProperty("atpnWarnQesitm")
            private String atpnWarnQesitm;

            @JsonProperty("atpnQesitm")
            private String atpnQesitm;

            @JsonProperty("intrcQesitm")
            private String intrcQesitm;

            @JsonProperty("seQesitm")
            private String seQesitm;

            @JsonProperty("depositMethodQesitm")
            private String depositMethodQesitm;

            @JsonProperty("openDe")
            private String openDe;

            @JsonProperty("updateDe")
            private String updateDe;

            @JsonProperty("itemImage")
            private String itemImage;

            @JsonProperty("bizrno")
            private String bizrno;
        }
    }
}