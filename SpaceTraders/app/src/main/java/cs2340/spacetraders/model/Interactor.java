package cs2340.spacetraders.model;

/**
 * Interface for our concrete interactors
 */
public abstract class Interactor {

    private Repository myRepository;

    protected Interactor(Repository repo) {
        myRepository = repo;
    }

    protected Interactor() {}

    protected Repository getRepository() {
        return myRepository;
    }
}
