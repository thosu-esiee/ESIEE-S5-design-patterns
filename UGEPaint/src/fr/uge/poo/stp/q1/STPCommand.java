package fr.uge.poo.stp.q1;

public interface STPCommand {
    void accept(STPCommandVisitor visitor);
}