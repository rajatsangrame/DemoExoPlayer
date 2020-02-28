//package com.example.rajat.demo.mgpl;
//
//
//import com.lib.model.feed.Trk;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Govind Singh on 3/12/19.
// * version V1.
// */
//public class FeedUtils {
//
//    //get the boomerang from track list
//    public static Trk getBoomerang(List<Trk> trkList) {
//        Trk boomerang = null;
//        for (int i = 0; i < trkList.size(); i++) {
//            Trk trk = trkList.get(i);
//            if (i == trkList.size() - 1 || trk.getTyp().equalsIgnoreCase("boomerang")) {
//                boomerang = trk;
//                return boomerang;
//            }
//        }
//        return boomerang;
//
//    }
//
//
//    //prepare final TrackList
//    public static List<Trk> addBoomerangToTrackList(List<Trk> trkList, Trk boomerangTrk) {
//        List<Trk> lTempTrackList = new ArrayList<Trk>();
//        for (int i = 0; i < trkList.size(); i++) {
//            Trk trk1 = trkList.get(i);
//            if (trk1.getTyp().equalsIgnoreCase("boomerang") || i == trkList.size() - 1) {
//                continue;
//            } else {
//                lTempTrackList.add(trk1);
//                lTempTrackList.add(boomerangTrk);
//            }
//        }
//        return lTempTrackList;
//
//    }
//
//}
