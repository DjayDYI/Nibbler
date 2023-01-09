package Statement.FlowTable;

import Statement.CaseStatement;
import Statement.SwitchStatement;

public class FlowSwitchCase {

    SwitchStatement current;

    FlowSwitchCase(){}

    public SwitchStatement getCurrent() {
        return current;
    }
    public void setCurrent(SwitchStatement current) {
        this.current = current;
    }

}
