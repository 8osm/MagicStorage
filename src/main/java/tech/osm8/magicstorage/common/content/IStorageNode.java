package tech.osm8.magicstorage.common.content;

import tech.osm8.magicstorage.common.StorageSystem;

public interface IStorageNode {

    StorageSystem getSystem();

    void setSystem(StorageSystem system);
}
