package dev.vnco.burst.shared.context;


public interface ChildrenContext extends ContextBase {

    String getParentLabel();

    void setParentLabel(String parentLabel);

}
