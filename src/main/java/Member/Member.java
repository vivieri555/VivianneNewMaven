package Member;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Member {
    private int id;
    private String name;
    private String status;
    private String history;

    public Member() {
    }

    public Member(int id, String name, String status, String history) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.history = history;
    }
    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlElement
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @XmlElement
    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
    public String toString(){
        return name;
    }
}
