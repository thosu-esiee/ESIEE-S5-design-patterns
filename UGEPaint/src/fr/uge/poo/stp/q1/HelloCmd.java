package fr.uge.poo.stp.q1;

public class HelloCmd implements STPCommand {
    @Override
    public void accept(STPCommandVisitor visitor) {
        visitor.visit(this);
    }
}
