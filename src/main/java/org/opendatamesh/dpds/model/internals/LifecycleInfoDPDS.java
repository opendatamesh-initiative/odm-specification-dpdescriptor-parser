package org.opendatamesh.dpds.model.internals;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.*;

@Data
public class LifecycleInfoDPDS {

    @Schema(description = "List of Lifecycle Task Info objects of the Lifecycle Info", required = true)
    private List<LifecycleTaskInfoDPDS> tasksInfo;

    public LifecycleInfoDPDS() {
        tasksInfo = new ArrayList<LifecycleTaskInfoDPDS>();
    }   

    public SortedSet<String> getStageNames() {
        SortedSet<String> stageNames = new TreeSet<>();
        for(LifecycleTaskInfoDPDS taskInfo: tasksInfo) {
            stageNames.add(taskInfo.getStageName());
        }
        return stageNames;
    }

    public List<LifecycleTaskInfoDPDS> getTasksInfo(String stageName) {

        List<LifecycleTaskInfoDPDS> stageTasksInfo = new ArrayList<LifecycleTaskInfoDPDS>();
        
        Objects.requireNonNull(stageName, "Parameter stageName cannot be null");
        
        for(LifecycleTaskInfoDPDS taskInfo: tasksInfo) {
            if(stageName.equals(taskInfo.getStageName())) {
                stageTasksInfo.add(taskInfo);
            }
        }
        
        return stageTasksInfo;
    }

}
