package rs.raf.gerumap.serializer;

import rs.raf.gerumap.model.repository.implementation.MindMap;

import java.io.File;
import java.nio.file.Path;
import java.util.UUID;

public interface ISerializer {
    void saveProject(File sourceSaveProject);
    void loadProject(File sourceLoadProject);
    void saveMindMapTemplate(File sourceSaveMindMapTemplate);
    void loadMindMapTemplate(File sourceLoadMindMapTemplate);
}
