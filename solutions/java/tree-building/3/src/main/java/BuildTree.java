import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class BuildTree {

    TreeNode buildTree(List<Record> records) throws InvalidRecordsException {
        if (records.isEmpty()) return null;
        records.sort(Comparator.comparing(Record::getRecordId));

        List<TreeNode> treeNodes = new ArrayList<>();
        for (int i = 0; i < records.size(); ++i) {
            Record r = records.get(i);
            if (r.getRecordId() != i
                    || (r.getRecordId() == 0 && r.getParentId() != 0)
                    || (r.getRecordId() > 0 && r.getRecordId() <= r.getParentId()))
                throw new InvalidRecordsException("Invalid Records");
            TreeNode t = new TreeNode(i);
            if (i > 0)
                treeNodes.get(records.get(i).getParentId()).getChildren().add(t);
            treeNodes.add(t);
        }
        return treeNodes.get(0);
    }
}