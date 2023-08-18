package core;

public interface LoadData {

    int findLuckyTicket(StateData stateData);

    void writeJson(DataForJson dataForJson, String fileJson);
}
