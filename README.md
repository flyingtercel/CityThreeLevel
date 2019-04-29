# 省市区三级联动
### 使用RecyclerView实现省市区三级联动
![image](https://github.com/flyingtercel/CityThreeLevel/tree/master/img/img2.gif)

### 1获取省市区三级联动json数据，存放在assets文件夹下
![文件路径](https://github.com/flyingtercel/CityThreeLevel/tree/master/img/img1.jpg)
### 2获取文件并解析
```
   AssetManager assets = getAssets();

   InputStream open = assets.open("address_select.txt");
   StringBuilder sb = new StringBuilder();
   byte[] inby = new byte[2048];
   int length = -1;
   while ((length = open.read(inby)) != -1) {
       sb.append(new String(inby, 0, length));
   }
   String json = new String(sb);
   AddressBean addressBean = new Gson().fromJson(json, AddressBean.class);
```
### 3创建适配器
```
因为使用相同视图，所以申明泛型来传递省市区地址
public class AddressAdapter<T> extends RecyclerView.Adapter<AddressAdapter<T>.VHolder> {

    private ArrayList<T> mList;
    ....
    public AddressAdapter(ArrayList<T> mList, Context context) {
       ....
        this.mList = mList;
    }
    ...

    在onBindViewHolder方法中只需要根绝泛型取出数据，设置在相应视图中
 T  addressBean = mList.get(i);
    if (addressBean instanceof ProviceBean){
        ProviceBean proviceBean = (ProviceBean) addressBean;
        ...
    }else if (addressBean instanceof CityBean){
        CityBean cityBean = (CityBean) addressBean;
        ...
    }else{
        AreaBean areaBean = (AreaBean) addressBean;
        ...
    }
}

```
### 4绑定RecyclerView的适配器
```
    mProviceAdapter = new AddressAdapter<>(mProvices, this);
    mCityAdapter = new AddressAdapter<>(mCitys, this);
    mAreaAdapter = new AddressAdapter<>(mAreas, this);

    mProviceRecyclerView.setAdapter(mProviceAdapter);
    mCityRecyclerView.setAdapter(mCityAdapter);
    mAreaRecyclerView.setAdapter(mAreaAdapter);
```
### 5使用接口回掉方式添加点击事件
```
在AddressAdapter中声明接口
    public interface OnAddressListener<T> {
        void onItemClick(View view, T cateBd, int postion);
    }

    public void setOnTimeListener(OnAddressListener onAddressListener) {
        this.onAddressListener = onAddressListener;
    }

在点击事件中处理回掉，并更新适配器
    mProviceAdapter.setOnTimeListener(new AddressAdapter.OnAddressListener<ProviceBean>() {
        @Override
        public void onItemClick(View view, ProviceBean cateBd, int postion) {
            ....
        }
    });
```