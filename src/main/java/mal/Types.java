package mal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Types {
    interface ILambda {
         MalType apply(MalList args) throws Exception;
    }
    interface MalType {

    }
    static class MalList implements MalType{
        List<MalType> malTypeList;

        public int size(){
            return malTypeList.size();
        }

        public MalType nth(Integer idx) {
            return (MalType)malTypeList.get(idx);
        }

        public MalList(MalType... malTypes) {
            this.malTypeList = new ArrayList<>();
            for (MalType mal:malTypes) {
                malTypeList.add(mal);
            }
        }

        public MalList(List<MalType> malTypeList) {
            this.malTypeList = malTypeList;
        }

        @Override
        public String toString() {
            return malTypeList + " => {MalList}";
        }

        public MalList slice(Integer start, Integer end) {
            return new MalList(malTypeList.subList(start, end));
        }
        public MalList slice(Integer start) {
            return slice(start, malTypeList.size());
        }
    }
    static class MalVector implements MalType{
        List<MalType> malTypeList;

        public MalVector(List<MalType> malTypeList) {
            this.malTypeList = malTypeList;
        }
        public MalVector(MalType... malTypes) {
            this.malTypeList = new ArrayList<>();
            for (MalType mal:malTypes) {
                malTypeList.add(mal);
            }
        }

        @Override
        public String toString() {
            return malTypeList + " => {MalVector}";
        }
    }
    static class MalHashMap implements MalType{
        HashMap<MalType,MalType> map;

        public MalHashMap() {
            map = new HashMap<>();
        }

        void put(MalType key,MalType value){
            map.put(key,value);
        }

        MalType get(MalType key){
            return map.get(key);
        }
    }
    static class MalInt implements MalType{
        int value;

        public MalInt(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value + " => {MalInt}";
        }
    }
    static class MalSymbol implements MalType{
        String value;

        public MalSymbol(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value + " => {MalSymbol}";
        }
    }

    static class MalConstant implements MalType{
        String constant;

        public MalConstant(String constant) {
            this.constant = constant;
        }

        @Override
        public String toString() {
            return constant + " => {MalConstant}";
        }
    }

    @FunctionalInterface
    interface MalFunc extends MalType, ILambda {
        MalType apply(MalList a) throws Exception;
    }

    static class MalBoolean implements MalType{
        boolean value;

        public MalBoolean(boolean value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return Boolean.toString(value);
        }
    }


}