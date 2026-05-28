public enum LogLevel {
    UNKNOWN("UNK",0),
    TRACE("TRC",1),
    DEBUG("DBG",2),
    INFO("INF",4),
    WARNING("WRN",5),
    ERROR("ERR",6),
    FATAL("FTL",42);

    private final String msg;
    private final int val;
    LogLevel(String msg, int val) {
        this.msg =msg;
        this.val = val;
    }
    public String getMsg() {
        return msg;
    }
    public int getVal() {
        return val;
    }
}

