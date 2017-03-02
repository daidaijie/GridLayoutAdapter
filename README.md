# GridLayoutAdapter
An adapter for Gridlayout

#### 使用注意
PS: 低配版Adapter，不带回收机制    
仿RecyclerView.Adpater配置而成，主要用于多行多列表格的绘制。    
目前只实现了notifyDataSetChanged()功能

#### 使用方法
和RecyclerView差不多
继承BaseGridAdapter类，实现`onCreateViewHolder`和`onBindViewHolder`方法，并实现一些表格相关的方法。

BaseGridAdapter方法简介(以下斜体均为抽象方法)

|方法|简介|
|:-:|:-:|
|*VH onCreateViewHolder(ViewGroup parent, int viewType)*|和RecyclerView的onCreateViewHolder相同|
|*void onBindViewHolder(VH holder, int column, int row, int viewType)*|和RecyclerView的onCreateViewHolder相同，不过位置是传入行和列作为参数|
|*boolean isGridExist(int column, int row)*|判断某行某列上是否存在单元格|
|*int getColumnCount();*|表格列数|
|*int getRawCount();*|表格行数|
|int getRawSpan(int column, int row)|获取某行某列的单元格占用行数，默认为1|
|int getColumnSpan(int column, int row)|获取某行某列的单元格占用列数，默认为1|
|getGridHeight()|获取单元格长度，默认为0。(为0时则默认每行平分表格长度)|
|getGridWidth()|获取单元格长度，默认为0。(为0时则默认每列平分表格宽度)|


#### Adapter和GridLayout关联 
使用`GridAdapter.attachTo(GridLayout);`即可关联Adapter和GridLayout，实现布局效果

数据集更新     
`
GridAdapter.notifyDataSetChanged();
`
