for arch in mac64 mac_arm64 linux64
do
  fname="${arch}.zip"
  curl -o $fname http://chromedriver.storage.googleapis.com/`curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE`/chromedriver_$fname
  unzip -o $fname -d bin/$arch
  rm $fname
done
