package rs.raf.gerumap.editorMindMap;

import lombok.Getter;
import lombok.Setter;
import rs.raf.gerumap.centralizedProjectView.ProjectView;
import rs.raf.gerumap.centralizedProjectView.mindMapCursorFactory.CursorFactoryEnums;
import rs.raf.gerumap.centralizedProjectView.mindMapCursorFactory.MindMapCursorFactory;
import rs.raf.gerumap.editorMindMap.editorState.*;

@Getter
@Setter
public class StateManager {

    private IState currentState;

    private AddTermState addTermState;
    private LinkElementsState linkElementsState;
    private DeleteState deleteState;
    private MoveState moveState;
    private SelectState selectState;

    private ProjectView projectView;

    public StateManager(ProjectView projectView) {

        initStates();
        currentState = selectState;   //default state on startup

        this.projectView = projectView;
    }

    private void initStates(){
        addTermState = new AddTermState();
        linkElementsState = new LinkElementsState();
        deleteState = new DeleteState();
        moveState = new MoveState();
        selectState = new SelectState();
    }

    public void setAddTermState(){
        currentState = addTermState;
        projectView.setCursorForProject(MindMapCursorFactory.getCursor(CursorFactoryEnums.ADD));
        System.out.println("Editor state set to: add term");
    }

    public void setDeleteState(){
        currentState = deleteState;
        projectView.setCursorForProject(MindMapCursorFactory.getCursor(CursorFactoryEnums.DELETE));
        System.out.println("Editor state set to: delete");
    }

    public void setLinkElementsState(){
        currentState = linkElementsState;
        projectView.setCursorForProject(MindMapCursorFactory.getCursor(CursorFactoryEnums.LINK));
        System.out.println("Editor state set to: link");
    }

    public void setMoveState(){
        currentState = moveState;
        projectView.setCursorForProject(MindMapCursorFactory.getCursor(CursorFactoryEnums.MOVE));
        System.out.println("Editor state set to: move");
    }

    public void setSelectState(){
        currentState = selectState;
        projectView.setCursorForProject(MindMapCursorFactory.getCursor(CursorFactoryEnums.SELECT));
        System.out.println("Editor state set to: select");
    }

}
