package com.example.galleryappdemo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
// model class
public class Pojo {

    @SerializedName("photos")
    @Expose
    private Photos photos;
    @SerializedName("stat")
    @Expose
    private String stat;

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }


    public class Photos {

        @SerializedName("page")
        @Expose
        private Integer page;
        @SerializedName("pages")
        @Expose
        private Integer pages;
        @SerializedName("perpage")
        @Expose
        private Integer perpage;
        @SerializedName("total")
        @Expose
        private Integer total;
        @SerializedName("photo")
        @Expose
        private List<Photo> photo;

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getPages() {
            return pages;
        }

        public void setPages(Integer pages) {
            this.pages = pages;
        }

        public Integer getPerpage() {
            return perpage;
        }

        public void setPerpage(Integer perpage) {
            this.perpage = perpage;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public List<Photo> getPhoto() {
            return photo;
        }

        public void setPhoto(List<Photo> photo) {
            this.photo = photo;
        }

    }

    public class Photo {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("owner")
        @Expose
        private String owner;
        @SerializedName("secret")
        @Expose
        private String secret;
        @SerializedName("server")
        @Expose
        private String server;
        @SerializedName("farm")
        @Expose
        private Integer farm;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("ispublic")
        @Expose
        private Integer ispublic;
        @SerializedName("isfriend")
        @Expose
        private Integer isfriend;
        @SerializedName("isfamily")
        @Expose
        private Integer isfamily;
        @SerializedName("url_s")
        @Expose
        private String urlS;
        @SerializedName("height_s")
        @Expose
        private Integer heightS;
        @SerializedName("width_s")
        @Expose
        private Integer widthS;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public String getServer() {
            return server;
        }

        public void setServer(String server) {
            this.server = server;
        }

        public Integer getFarm() {
            return farm;
        }

        public void setFarm(Integer farm) {
            this.farm = farm;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getIspublic() {
            return ispublic;
        }

        public void setIspublic(Integer ispublic) {
            this.ispublic = ispublic;
        }

        public Integer getIsfriend() {
            return isfriend;
        }

        public void setIsfriend(Integer isfriend) {
            this.isfriend = isfriend;
        }

        public Integer getIsfamily() {
            return isfamily;
        }

        public void setIsfamily(Integer isfamily) {
            this.isfamily = isfamily;
        }

        public String getUrlS() {
            return urlS;
        }

        public void setUrlS(String urlS) {
            this.urlS = urlS;
        }

        public Integer getHeightS() {
            return heightS;
        }

        public void setHeightS(Integer heightS) {
            this.heightS = heightS;
        }

        public Integer getWidthS() {
            return widthS;
        }

        public void setWidthS(Integer widthS) {
            this.widthS = widthS;
        }

    }


}