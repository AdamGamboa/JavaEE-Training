package com.training.javaee.jaxrs.common.util;

import java.util.Iterator;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 *
 * @author training
 */
public class JSFUtil {

    public static void ensureAddErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JSFUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    /**
     * <p>
     * Return the {@link UIComponent} (if any) with the specified
     * <code>id</code>, searching recursively starting at the specified
     * <code>base</code>, and examining the base component itself, followed by
     * examining all the base component's facets and children. Unlike
     * findComponent method of {@link javax.faces.component.UIComponentBase},
     * which skips recursive scan each time it finds a {@link NamingContainer},
     * this method examines all components, regardless of their namespace
     * (assuming IDs are unique).
     *
     * @param base Base {@link UIComponent} from which to search
     * @param id Component identifier to be matched
     */
    public static UIComponent findComponent(UIComponent base, String id) {
        if (id == null || "".equals(id)) {
            return null;
        }
        // Is the "base" component itself the match we are looking for?
        if (id.equals(base.getId())) {
            return base;
        }
        // check for direct child
        UIComponent result = base.findComponent(id);
        if (result != null) {
            return result;
        }

        // Search through our facets and children
        UIComponent kid = null;
        Iterator kids = base.getFacetsAndChildren();
        while (kids.hasNext() && (result == null)) {
            kid = (UIComponent) kids.next();
            if (id.equals(kid.getId())) {
                result = kid;
                break;
            }
            result = findComponent(kid, id);
            if (result != null) {
                break;
            }
        }
        return result;
    }
}
