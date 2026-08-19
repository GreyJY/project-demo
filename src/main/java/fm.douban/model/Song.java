//这是个模型类，用一个Java对象来表示一整首歌的数据，后面全是安全封装
package fm.douban.model;

public class Song {
  private String id;
  private String name;
  private String lyrics;
  private String subjectId;
  public String getId() {
        return id;
    } public void setId(String id) {
        this.id = id;
    }
  public String getName() {
        return name;
    }
 public void setName(String name) {
        this.name = name;
    }
  public String getLyrics() {
        return lyrics;
    }
  public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
 public String getSubjectId() {
        return subjectId;
    }
 public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

}
/*浏览器请求
  ↓
Controller
  ↓
Service
  ↓
Song对象
  ↓
数据库*/
