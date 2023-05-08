package rs.raf.gerumap.serializer;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import rs.raf.gerumap.Main;
import rs.raf.gerumap.centralizedProjectView.MindMapView;
import rs.raf.gerumap.core.ApplicationFramework;
import rs.raf.gerumap.errorHandling.message.abstractionAndEnums.MessageDescription;
import rs.raf.gerumap.globalView.frame.MainFrame;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.model.repository.implementation.MindMap;
import rs.raf.gerumap.model.repository.implementation.Project;
import rs.raf.gerumap.model.repository.implementation.Term;
import rs.raf.gerumap.tree.view.MapTreeView;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class JacksonSerializer implements ISerializer{

    @Override
    public void saveProject(File sourceSaveProject) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);

        Project project = MainFrame.getInstance().getCurrentProjectView().getProject();
        sourceSaveProject.getParentFile().mkdirs();

        try {

            sourceSaveProject.createNewFile();

            if(project != null){
                objectMapper.writeValue(sourceSaveProject, project);
                logSuccessfulSave(project, sourceSaveProject);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void loadProject(File sourceLoadProject) {

        if(!sourceLoadProject.getName().contains("."))
            sourceLoadProject = new File(sourceLoadProject + ".json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);

        try {

            Project project = objectMapper.readValue(sourceLoadProject, Project.class);

            project.setParent(ApplicationFramework.getInstance().getIMapRepository().getProjectExplorer());
            project.setChildrenParents();

            ApplicationFramework.getInstance().getIMapRepository().getProjectExplorer().addChild(project);

        } catch (IOException e) {
            logUnsuccessfulLoad(sourceLoadProject);
        }
    }

    @Override
    public void saveMindMapTemplate(File sourceSaveMindMapTemplate) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);

        MindMapView mindMapView = (MindMapView) ((JScrollPane) MainFrame.getInstance().getCurrentProjectView().getSelectedComponent()).getViewport().getView();
        MindMap currentMindMap = mindMapView.getMindMap();
        sourceSaveMindMapTemplate.getParentFile().mkdirs();

        try {

            sourceSaveMindMapTemplate.createNewFile();

            if(currentMindMap != null){
                objectMapper.writeValue(sourceSaveMindMapTemplate, currentMindMap);
                logSuccessfulSave(currentMindMap, sourceSaveMindMapTemplate);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void loadMindMapTemplate(File sourceLoadMindMapTemplate) {

        if (MainFrame.getInstance().getCurrentProjectView() == null) {
            ApplicationFramework.getInstance().getMessageGeneratorImplementation().generateMessage(MessageDescription.NO_NODE_SELECTED, null);
            return;
        }

        MindMapView mindMapView = (MindMapView) ((JScrollPane) MainFrame.getInstance().getCurrentProjectView().getSelectedComponent()).getViewport().getView();
        MindMap currentMindMap = mindMapView.getMindMap();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);

        try {

            MindMap mindMap = objectMapper.readValue(sourceLoadMindMapTemplate, MindMap.class);

            currentMindMap.mergeMindMaps(mindMap);

        } catch (IOException e) {
            logUnsuccessfulLoad(sourceLoadMindMapTemplate);
        }
        SwingUtilities.updateComponentTreeUI(mindMapView);
    }

    private void logSuccessfulSave(Object savedItem, Object savedPath){

        String[] source = new String[]{savedItem.toString(), savedPath.toString()};
        ApplicationFramework.getInstance().getMessageGeneratorImplementation().generateMessage(MessageDescription.SUCCESSFULLY_SAVED, source);

    }

    private void logUnsuccessfulLoad(Object source){

        ApplicationFramework.getInstance().getMessageGeneratorImplementation().generateMessage(MessageDescription.UNABLE_TO_LOAD_FILE, source);

    }
}
