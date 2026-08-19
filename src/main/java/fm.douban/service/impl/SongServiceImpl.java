//这个 SongServiceImpl.java 就是刚刚说的 Service层的具体实现类。它的作用：负责处理歌曲相关的业务逻辑，比如根据ID查歌曲、根据专题ID查歌曲列表
package fm.douban.service.impl;

import fm.douban.model.Song;
import fm.douban.service.SongService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//接口实现类，这种写法方便后续更改业务流程，告诉spring这是业务层组件交给它保管，后面controler可以调用他,调用直接用接口名字降低耦合
@Service
public class SongServiceImpl implements SongService {
    private static Map<String, Song> songMap = new HashMap<>();//创建一个临时数据库，只是为了更好了解这个机理
    static {
        Song song = new Song();
        song.setId("001");
        song.setSubjectId("s001");
        song.setLyrics("...");
        song.setName("成都");
        songMap.put(song.getId(), song);
    }//static代码库，项目启动的时候自动启动创建这首歌
    @Override
    public Song get(String songId) {
        return songMap.get(songId);//根据歌曲id查询功能，执行查询001，然后找到对应的song对象
    }
    @Override
    public List<Song> list(String subjectId) {
        List<Song> songs = new ArrayList<>();//创建一个空列表，把找到的歌曲放里边songEntry.getValue()
        for (Map.Entry<String, Song> songEntry : songMap.entrySet()) {//遍历所有map里面的歌曲，循环一次拿一个song
/*Song
  songEntry.getValue()
得到歌曲对象：
Song*/
            if (/*Map.Entry*/songEntry.getValue().getSubjectId().equals(subjectId)) {
                songs.add(songEntry.getValue());//根据专题找歌曲
            }
        }
        return songs;
    }
}
