# coupon
优惠券
mvvm+databinding
## takephoto
[图片压缩 ](https:github.com/crazycodeboy/TakePhoto)

[AdvancedLuban 鲁班压缩](https:github.com/shaohui10086/AdvancedLuban)

  * 最新版配置
  File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
              if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
              Luban.compress(this, file)
                      .setMaxSize(1024)                 限制最终图片大小（单位：Kb）
                      .setMaxHeight(1920)              限制图片高度
                      .setMaxWidth(1080)               限制图片宽度
                      .setCompressFormat(JPEG)             自定义压缩图片格式，目前只支持：JPEG(0), WEBP(2);png不支持压缩图片品质
                      .putGear(Luban.CUSTOM_GEAR)      使用 CUSTOM_GEAR 压缩模式
                      .asObservable()
                      .subscribe(new Consumer<File>() {
                          @Override
                          public void accept(File file) throws Exception {
                              LogT.w("压缩地址="+file.getAbsolutePath());
                              mImageViews.get(0).setImageURI(Uri.fromFile(file));
                          }
                      }, new Consumer<Throwable>() {
                          @Override
                          public void accept(Throwable throwable) throws Exception {
                              LogT.w("压缩异常："+throwable.getMessage());
                          }
                      });

## BaseObservable

[databinding-Bean文件继承BaseObservable的用法](https://blog.csdn.net/u010687392/article/details/47314431)


Data Binding中如果我们直接修改Model实体对象（也就是POJO）中的数据，这些数据并不能直接更新到UI，
所以Data Binding给了我们一套很好的通知机制，分别有三类： 
Observable objects, observable fields, and observable collections，分别表示观察对象、观察字段、
观察集合，若相应的对象、字段、集合中数据变化时候，那么UI将会自动更新数据。
* 用法一：BaseObservable类
Observable是个接口，Google为我们提供了一个BaseObservable类，我们只要把Model类继承自它就获得了
通知UI更新数据的能力了，然后再getter方法上添加Bindable注解，在setter方法中使用notifying提醒UI更新数据。

首先我们需要在getter方法上添加Bindable注解后，Bindable注解会自动生成一个BR类，该类位于app module包下，
通过BR类我们设置更新的数据，当Model中的数据发生变化时，setter方法中的notifyPropertyChanged()
就会通知UI更新数据了。

private static class User extends BaseObservable {
   private String userName;
   private String userPassword;
   public User(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }
    @Bindable
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

 * 用法二： ObservableFields类，使用它我们可以简化我们的Model类

    public class User{
        public final ObservableField<String> userName = new ObservableField<>();
        public final ObservableField<String> userPassword = new ObservableField<>();
    }
    ***
    获取数据：

    User user = new User();
    user.userName.set("sunzxyong");
    user.userPassword.set("12345678");
    String userName = user.userName.get();
    String userPassword = user.userPassword.get();
    ***
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            ActivityMainBinding mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
            final User user = new User();
            user.userName.set("sunzxyong");
            user.userPassword.set("12345678");
            mBinding.setUser(user);
            mBinding.btn.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {
                    user.userName.set("hello");
                    user.userPassword.set("87654321");
                }
            });
        }
## greendao
[greendao](https://blog.csdn.net/qq_38520096/article/details/78833801)

增删改查

  //获取操作类
        appDataHelper = DbUtil.getAppDataHelper();

findViewById(R.id.btn_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<100;i++){
                    AppData addData = new AppData();
                    addData.setBgColor(R.color.colorAccent);
                    addData.setLabel("新增db标题"+i);
                    addData.setHasUpdate(true);
                    addData.setLoadedSize(1045+i*10);
                    addData.setBgIndex(1+i);
                    addDataList.add(addData);
                    appDataHelper.save(addData);
                }
                tv_db.setText(addDataList.toString());
            }
        });
        findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataList = appDataHelper.queryAll();
                if (addDataList.size() > 0) {
                    AppData deleData = addDataList.get(addDataList.size() - 1);
                    //删除上一条记录
                    appDataHelper.delete(deleData);
                }else{
                    tv_db.setText("");
                    Toast.makeText(MainActivity.this,"没有数据",Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.btn_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataList = appDataHelper.queryAll();
                LogT.w("所有列表："+addDataList.toString());
                if (addDataList.size() > 0) {
                    LogT.w("查询："+addDataList.toString());
                    tv_db.setText(addDataList.toString());
                }else{
                    tv_db.setText("");
                    Toast.makeText(MainActivity.this,"没有数据",Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.btn_4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDataList = appDataHelper.queryAll();
                if (addDataList.size() > 0) {
                    List<AppData> queryList= new ArrayList<>();
                    //获取BgIndex=3的数据
                    Query<AppData> query = appDataHelper.queryBuilder()
                            .where(AppDataDao.Properties.BgIndex.eq("3"))
                            .build();
                    queryList = query.list();
                    LogT.w("查询bgIndex=3："+queryList.toString());
                    tv_db.setText("查询bgIndex=3："+queryList.toString());
                }else{
                    tv_db.setText("");
                    Toast.makeText(MainActivity.this,"没有数据",Toast.LENGTH_SHORT).show();
                }
            }
        });