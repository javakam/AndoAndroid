### Parcelable
---

- Parcel可以再Binder中自由传输

- writeToParcel负责序列化功能，反序列化则由CREATOR完成

- MyUser(Parcel in)方法中，由于MyBook是另一个可序列化对象，所以它的反序列化过程需要传递当前线程的上下文类加载器，否则会报无法找到类的错误
```
protected MyUser(Parcel in) {
    this.uname = in.readString();
    //1 《艺术探索》
    this.myBook = in.readParcelable(Thread.currentThread().getContextClassLoader());
    //2 default generate
      this.myBook = in.readParcelable(MyBook.class.getClassLoader());
    this.books = in.createTypedArrayList(MyBook.CREATOR);
}
```

- 系统提供的实现了Parcelabel接口的类，可以直接进行序列化操作的，比如：Intent、Bitmap、Bundle。
同时List和Map也是可以序列化，前提是它们内部的元素是可以序列化的。

- Serializble和Parcelable的区别 <br><br>
![](.document_images\Serializable和Parcelable的区别.png)