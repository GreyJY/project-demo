package fm.douban.service.impl;

import fm.douban.model.Song;
import fm.douban.model.Subject;
import fm.douban.service.SongService;
import fm.douban.service.SubjectService;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//依旧业务逻辑层
@Service
public class SubjectServiceImpl implements SubjectService {
    private songService songService;

    //缓存所有专辑数据
    private static Map<String, Subject> subjectMap = new HashMap<>();
    static {
        Subject subject = new Subject();
        subject.setId("s001");
        subjectMap.put(subject.getId(), subject);
    }
//创建一个临时数据库
    @Override
    public Subject get(String subjectId) {
        Subject subject = subjectMap.get(subjectId);
        //调用 SongService 获取专辑歌曲
        List<Song> songs = songService.list(subjectId);
        subject.setSongs(songs);
        return subject;
    }//跟前面很像的专辑业务流程

    public void setSongService(SongService songService) {
        this.songService = songService;
    }

