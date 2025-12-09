package Member;

import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

import java.util.Scanner;

public class MembershipService{
    Scanner input = new Scanner(System.in);
    MemberRegistry memberRegistry;
    TextField text;

    public MembershipService(MemberRegistry memberRegistry){
        this.memberRegistry = memberRegistry;
    }

    //Hämta observablelist  objekt istället för arraylistMembers
    public Member searchMemberList(String searchedMember){
        for(Member member: memberRegistry.getMembers()){
            if(member.getName().contains(searchedMember)){
                return member;
            }
        } return null;
    }

    public ObservableList<Member> getMembers() {
        return memberRegistry.getMembers();
    }
    public void changeHistory(Member member){
        System.out.println("Skriv in ändring till historiken");
        member.setHistory(input.nextLine());
    }
    public int addId(TextField addMemText, Member member3) {
        // int id = Integer.parseInt(addMemText.getText());
        int id = 0;
        try {
            id = Integer.parseInt(addMemText.getText());
            member3.setId(id);
        }
        catch (NumberFormatException e2) {
            addMemText.setText("Inte ett giltigt nummer");
        } return id;
    }
    public void delMember(Member searchedMember) {
        if (searchedMember == null) {
            TextField del = new TextField("Medlemmen finns inte");
            del.getText();
        } else {
            memberRegistry.delete(searchedMember);
        }
    }
    public void change(Member member, TextField idInput, TextField nameInput, TextField changeStatus, TextField changeHistory ) {

        member.setId(addId(idInput, member));
        member.setName(nameInput.getText());
        member.setStatus(changeStatus.getText());
        member.setHistory(changeHistory.getText());
    }
}
