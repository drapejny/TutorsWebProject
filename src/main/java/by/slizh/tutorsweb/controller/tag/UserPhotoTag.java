package by.slizh.tutorsweb.controller.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;

public class UserPhotoTag extends TagSupport {

    private String photo;
    private int height;
    private int width;

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int doStartTag() throws JspException {
        StringBuilder stringBuilder = new StringBuilder("<img src='");
        if (photo.equals("")) {
            stringBuilder.append(pageContext.getServletContext().getContextPath());
            stringBuilder.append("/img/user.png' ");
        } else {
            stringBuilder.append("data:image/jpg;base64,");
            stringBuilder.append(photo);
            stringBuilder.append("' ");
        }
        stringBuilder.append("width='" + width + "' ");
        stringBuilder.append("height='" + height + "'>");
        try {
            pageContext.getOut().write(stringBuilder.toString());
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
