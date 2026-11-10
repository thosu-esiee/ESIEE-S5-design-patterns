package fr.uge.poo.stp.q1;

public class TrivialeVisitor implements STPCommandVisitor {
    @Override
    public void visit(HelloCmd cmd) {
        System.out.println("Au revoir");
    }

    @Override
    public void visit(StartTimerCmd cmd) {
        System.out.println("non implemente");
    }

    @Override
    public void visit(StopTimerCmd cmd) {
        System.out.println("non implemente");
    }

    @Override
    public void visit(ElapsedTimeCmd cmd) {
        System.out.println("non implemente");
    }
}
