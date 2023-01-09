package Statement.FlowTable;

import Statement.IfStatement;

public class Flow {

    IfStatement current;

    Flow(){
        current = null;
    }

    public void setCurrent(IfStatement current) {
        this.current = current;
    }

    public IfStatement getCurrent() {
        return current;
    }
}
