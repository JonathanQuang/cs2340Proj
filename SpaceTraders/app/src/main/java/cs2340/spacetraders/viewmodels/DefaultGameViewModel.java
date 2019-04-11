package cs2340.spacetraders.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

/**
 * The default game view that gets called in the beginning of the app
 */
public class DefaultGameViewModel extends AndroidViewModel {
    /**
     * @param application the current application
     */
    public DefaultGameViewModel(@NonNull Application application) {
        super(application);
    }
}
