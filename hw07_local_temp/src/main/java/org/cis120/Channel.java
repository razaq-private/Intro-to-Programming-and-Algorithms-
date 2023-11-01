package org.cis120;
import java.util.*;

public class Channel implements Comparable<Channel> {
    private String name;
    private String owner;
    private boolean inviteStatus;
    private Set<String> members;

    public Channel(String name, String owner, boolean inviteStatus) {
        this.name = name;
        this.owner = owner;
        this.inviteStatus = inviteStatus;
        Set<String> members = new TreeSet<String>();
        //add the owner first as a member of the set
        members.add(owner);
        this.members = members;
    }

    //functions that get values
    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public boolean getInviteStatus() {
        return inviteStatus;
    }

    public Set<String> getMembers() {
       /* Set<String> membersName = new TreeSet<String>();
        for (String i : members){
            membersName.add(i);
        }*/

        return members;
    }

    //functions that set a new value
    public void setName(String newName) {
        this.name = newName;
    }

    public void setOwner(String newOwner) {
        this.owner = newOwner;
    }

    public void setMembers(Set<String> newMembers) {
        this.members = newMembers;
    }

    public void setInviteStatus(boolean status) {
        this.inviteStatus = status;
    }

    //remainder of the functions similar to servermodel
    public boolean userInChannel(String nickname) {
        boolean state = false;
        for (String x : members) {
            if (nickname.equals(x)) {
                state = true;
            }
        }
        return state;
    }

    //add user
    public void addUser(String a) {
        members.add(a);
    }

    public void removeUser(String a) {
        members.remove(a);
    }

    @Override
    public int compareTo(Channel x) {
        if (x instanceof Channel) {
            return members.size() - x.members.size();
        } else {
            return -1;
        }
    }



}
