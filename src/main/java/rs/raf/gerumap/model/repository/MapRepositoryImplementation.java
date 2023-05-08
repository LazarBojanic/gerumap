package rs.raf.gerumap.model.repository;

import lombok.Getter;
import lombok.Setter;
import rs.raf.gerumap.core.IMapRepository;
import rs.raf.gerumap.model.repository.composite.MapNode;
import rs.raf.gerumap.model.repository.composite.MapNodeComposite;
import rs.raf.gerumap.model.repository.implementation.ProjectExplorer;
import rs.raf.gerumap.tree.MapTreeImplementation;
import rs.raf.gerumap.tree.view.MapTreeView;

@Getter
@Setter
public class MapRepositoryImplementation implements IMapRepository {

    private MapTreeImplementation mapTreeImplementation;
    private ProjectExplorer projectExplorer;

    public MapRepositoryImplementation(){
        projectExplorer = new ProjectExplorer("Project Explorer");
        mapTreeImplementation = new MapTreeImplementation();
    }
    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }

    @Override
    public void addChild(MapNodeComposite parent, MapNode child) {

    }

    public MapTreeView takeGeneratedTree(){
        return mapTreeImplementation.generateTree(this.projectExplorer);
    }
}
