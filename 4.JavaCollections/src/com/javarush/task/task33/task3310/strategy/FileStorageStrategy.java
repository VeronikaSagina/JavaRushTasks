package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    private int size = 0;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    private long maxBucketSize;
    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    public FileStorageStrategy() {
        for (int i = 0; i < DEFAULT_INITIAL_CAPACITY; i++) {
            table[i] = new FileBucket();
        }
    }

    public int hash(Long k) {
        int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }

    public int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    public Entry getEntry(Long key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        if (table[index] != null) {
            Entry entry = table[index].getEntry();
            while (entry != null) {
                if (entry.getKey().equals(key)) {
                    return entry;
                }
                entry = entry.next;
            }
        }
        return null;
    }


    public void resize(int newCapacity) {
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
    }

    private void transfer(FileBucket[] newTable) {
        for (FileBucket bucketInTable : table) {
            if (bucketInTable == null) {
                continue;
            }
            Entry entry = bucketInTable.getEntry();
            while (entry != null) {
                Entry next = entry.next;
                int newIndex = indexFor(entry.hash, newTable.length);
                if (newTable[newIndex] == null) {
                    entry.next = null;
                    newTable[newIndex] = new FileBucket();
                } else {
                    entry.next = newTable[newIndex].getEntry();
                }
                newTable[newIndex].putEntry(entry);
                entry = next;
            }
            bucketInTable.remove();
        }
    }

    public void addEntry(int hash, Long key, String value, int bucketIndex) {
        Entry e = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        size++;
        if (table[bucketIndex].getFileSize() > bucketSizeLimit) {
            resize(2 * table.length);
        }
    }

    public void createEntry(int hash, Long key, String value, int bucketIndex) {
        table[bucketIndex] = new FileBucket();
        table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        size++;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        for (FileBucket aTable : table) {
            if (aTable == null) {
                continue;
            }
            Entry entry = aTable.getEntry();
            while (entry != null) {
                if (entry.value.equals(value)) {
                    return true;
                }
                entry = entry.next;
            }
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        if (table[index] != null) {
            Entry entry = table[index].getEntry();
            while (entry != null) {
                if (entry.getKey().equals(key)) {
                    entry.value = value;
                    return;
                }
                entry = entry.next;
            }
            addEntry(hash, key, value, index);
        } else {
            createEntry(hash, key, value, index);
        }
    }

    @Override
    public Long getKey(String value) {
        for (FileBucket aTable : table) {
            if (aTable == null) {
                continue;
            }
            Entry entry = aTable.getEntry();
            while (entry != null) {
                if (entry.value.equals(value)) {
                    return entry.getKey();
                }
                entry = entry.next;
            }
        }
        return 0L;
    }

    @Override
    public String getValue(Long key) {
        Entry entry = getEntry(key);
        if (entry == null) {
            return null;
        }
        return entry.value;
    }


}
