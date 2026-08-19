# project-demo

#Controller管“请求进来”，Service管“事情怎么做”，Model管“数据长什么样”。
用户浏览器
    |
    ↓
Controller（接收请求）
    |
    ↓
Service（处理业务）
    |
    ↓
Model（封装数据）
    |
    ↓
返回页面/数据
    |
    ↓
浏览器展示

project-demo

├── controller
│
│     接收用户请求
│
│
├── service
│
│     处理业务逻辑
│
│
├── model
│
│     保存数据结构
│
│
├── interceptor
│
│     登录检查
│
│
└── resources
      |
      ├── templates
      │       html页面
      │
      └── static
              css/js/图片
