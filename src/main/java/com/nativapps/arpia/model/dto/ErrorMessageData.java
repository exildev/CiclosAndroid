package com.nativapps.arpia.model.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Alfonso Lenes Salas <luislenes02@gmail.com>
 * @version 1.0
 */
@XmlRootElement
public class ErrorMessageData {

    private final String documentation = "No documentation for now";

    private int statusCode;

    private List<MessageData> messages;

    public ErrorMessageData() {
        this.messages = new ArrayList<>();
    }

    public ErrorMessageData(int statusCode) {
        this.statusCode = statusCode;
        this.messages = new ArrayList<>();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<MessageData> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageData> messages) {
        this.messages = messages;
    }

    @XmlElement
    public String getDocumentation() {
        return documentation;
    }

    public void addMessage(String message) {
        this.messages.add(new MessageData(message));
    }

}
