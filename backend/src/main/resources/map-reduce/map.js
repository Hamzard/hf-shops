function () {
    this.preferredShops.forEach((shop)=>{
        emit(shop, 1)
     })
}