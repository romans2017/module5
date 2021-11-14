package ua.petstore.client.models;

import java.util.Arrays;

public class Pet implements ApiModel {
    private Integer id;
    private Category category;
    private String name;
    private String[] photoUrls;
    private Tag[] tags;
    private String status;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public static class Category {
        private final Integer id;
        private final String name;

        private Category(Category category) {
            this.id = category.id;
            this.name = category.name;
        }

        public Category(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Tag {
        private final Integer id;
        private final String name;

        private Tag(Tag tag) {
            this.id = tag.id;
            this.name = tag.name;
        }

        public Tag(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Pet newPet;

        private Builder() {
            newPet = new Pet();
        }

        public Builder withId(Integer id) {
            newPet.id = id;
            return this;
        }

        public Builder withCategory(Category category) {
            newPet.category = new Category(category);
            return this;
        }

        public Builder withName(String name) {
            newPet.name = name;
            return this;
        }

        public Builder withUrls(String[] photoUrls) {
            newPet.photoUrls = Arrays.copyOf(photoUrls, photoUrls.length);
            return this;
        }

        public Builder withTags(Tag[] tags) {
            newPet.tags = Arrays
                    .stream(tags)
                    .map(Tag::new)
                    .toArray(Tag[]::new);
            return this;
        }

        public Builder withStatus(String status) {
            newPet.status = status;
            return this;
        }

        public Pet build() {
            return newPet;
        }
    }
}
