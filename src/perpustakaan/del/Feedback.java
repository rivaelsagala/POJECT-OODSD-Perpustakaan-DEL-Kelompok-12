/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perpustakaan.del;

/**
 *
 * @author RIVAEL S S
 */
import java.util.Scanner;

public class Feedback implements Notifications {
    public String feedback;

    public Feedback() {
    }

    public void setFeedback() {
        Scanner input = new Scanner(System.in);
        this.feedback = input.nextLine();
    }

    public String getFeedback() {
        return this.feedback;
    }

    @Override
    public void display() {
        System.out.println(getFeedback());
    }
}