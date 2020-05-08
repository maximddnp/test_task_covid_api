package org.test.covid_api.domain;

public enum Countries {
    FRANCE("france"), UKRAINE("ukraine");

    private String slug;

    Countries(String slug) {
        this.slug = slug;
    }
    public String getSlug() {
        return slug;
    }
    @Override
    public String toString() {
        return "Country{" +
                "slug='" + slug + '\'' +
                '}';
    }
}
