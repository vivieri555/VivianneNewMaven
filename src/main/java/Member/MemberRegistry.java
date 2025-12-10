package Member;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
@XmlRootElement
public class MemberRegistry {

    private ObservableList<Member> members = FXCollections.observableArrayList();


    public MemberRegistry(){}
    public MemberRegistry(ObservableList<Member> members){
        this.members = members;
    }
    //för att hämta medlemmar i main sen med getter
    @XmlElement
    public ObservableList<Member>getMembers(){
        return members;
    }
    //lägga till medlem
    public void add(Member member){
        members.add(member);
    }
    // remove medlem
    public void delete(Member member) {
        {
            members.remove(member);
        }
    }
}
