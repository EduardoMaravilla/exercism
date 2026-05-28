import java.util.ArrayList;
import java.util.Comparator;

class BuildTree {

    TreeNode buildTree(ArrayList<Record> records) throws InvalidRecordsException {
        records.sort(Comparator.comparing(Record::getRecordId));
        ArrayList<Integer> orderedRecordIds = new ArrayList<>(records.stream().map(Record::getRecordId).toList());

        if (records.size() > 0 && (orderedRecordIds.get(orderedRecordIds.size() - 1) != orderedRecordIds.size() - 1 || orderedRecordIds.get(0) != 0)) {
            throw new InvalidRecordsException("Invalid Records");
        }


        ArrayList<TreeNode> treeNodes = new ArrayList<>();

        for (Integer orderedRecordId : orderedRecordIds) {
            for (Record record : records) {
                if (orderedRecordId == record.getRecordId()) {
                    if (record.getParentId() != 0 && (record.getRecordId() == 0 || record.getRecordId() == record.getParentId() )|| record.getRecordId() < record.getParentId()) {
                        throw new InvalidRecordsException("Invalid Records");
                    }
                    treeNodes.add(new TreeNode(record.getRecordId()));
                }
            }
        }

        for (int i = 0; i < orderedRecordIds.size(); i++) {
            TreeNode parent;
            for (TreeNode n : treeNodes) {
                if (i == n.getNodeId()) {
                    parent = n;
                    for (Record record : records) {
                        if (record.getParentId() == i) {
                            for (TreeNode node : treeNodes) {
                                if (node.getNodeId() == 0) {
                                    continue;
                                }
                                if (record.getRecordId() == node.getNodeId()) {
                                    parent.getChildren().add(node);
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }

        if (treeNodes.size() > 0) {
            return treeNodes.get(0);
        }
        return null;
    }
}