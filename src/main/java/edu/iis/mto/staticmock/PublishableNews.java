package edu.iis.mto.staticmock;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PublishableNews {

    public static PublishableNews create() {
        return new PublishableNews();
    }

    private final List<String> publicContent = new ArrayList<>();
    private final List<String> subscribentContent = new ArrayList<>();

    public void addPublicInfo(String content) {
        this.publicContent.add(content);

    }

    public void addForSubscription(String content, SubsciptionType subscriptionType) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj.getClass() != getClass() || obj == null)
            return false;
        PublishableNews o = (PublishableNews) obj;
        return Objects.equals(publicContent, o.publicContent) &&
                Objects.equals(subscribentContent, o.subscribentContent);
    }

}
