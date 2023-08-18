package core;

import java.util.ArrayList;
import java.util.List;

public class StateData {

    private StringBuilder curNum;
    private List<String> luckyList = new ArrayList<>();
    private int countOne = 0;
    private int countAll = 0;
    private int numbers;
    private StringBuilder helperSb = new StringBuilder();
    private StringBuilder ticket = new StringBuilder();

    public StateData(int numbers) {
        this.numbers = numbers;
    }

    public StateData() {
    }

    public int getNumbers() {
        return numbers;
    }

//    public String getCurNum() {
//        return curNum;
//    }
//
//    public void setCurNum(String curNum) {
//        this.curNum = curNum;
//    }

    public StringBuilder getTicket() {
        return ticket;
    }

    public StringBuilder getCurNum() {
        return curNum;
    }

    public void setCurNum(StringBuilder curNum) {
        this.curNum = curNum;
    }

    public List<String> getLuckyList() {
        return luckyList;
    }

    public void setLuckyList(List<String> luckyList) {
        this.luckyList = luckyList;
    }

    public int getCountOne() {
        return countOne;
    }

    public void setCountOne(int countOne) {
        this.countOne = countOne;
    }

    public int getCountAll() {
        return countAll;
    }

    public void setCountAll(int countAll) {
        this.countAll = countAll;
    }

    public StringBuilder getHelperSb() {
        return helperSb;
    }

    @Override
    public String toString() {
        return "StateData{" +
                "curNum='" + curNum + '\'' +
                ", luckyList=" + luckyList +
                ", countOne=" + countOne +
                ", countAll=" + countAll +
                '}';
    }
}
