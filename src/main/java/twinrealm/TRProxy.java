package twinrealm;

/**
 * Created by lukas on 15.09.14.
 */
public interface TRProxy
{
    void registerRenderers();

    void loadConfig(String configID);
    
    void registerReloadListeners();
}
