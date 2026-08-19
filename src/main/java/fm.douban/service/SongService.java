//前面都是为了接收信息用的方法，而这个service里面的方法是实现业务逻辑的，这是个接口，为了解耦合

package fm.douban.service;

import fm.douban.model.Song;

import java.util.List;

public interface SongService {

    Song get(String songId);

    List<Song> list(String subjectId);

}
