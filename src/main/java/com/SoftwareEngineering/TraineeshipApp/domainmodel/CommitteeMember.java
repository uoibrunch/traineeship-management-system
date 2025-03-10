package com.SoftwareEngineering.TraineeshipApp.domainmodel;

import jakarta.persistence.*;


@Entity
@Table(name = "committee_members")
public class CommitteeMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "committee_member_id")
    private int committeeMemberId;

    @Column(name = "user_name")
    private String username;
   
    public int getCommitteeMemberId() {
        return committeeMemberId;
    }

    public void setCommitteeMemberId(int committeeMemberId) {
        this.committeeMemberId = committeeMemberId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }

   
}
