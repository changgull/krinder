if [ -z $1 ]
then
  osList="mac64 mac_arm64 linux64"
else
  osList=$1
fi

for arch in $osList
do
  fname="${arch}.zip"
  curl -o $fname http://chromedriver.storage.googleapis.com/`curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE`/chromedriver_$fname
  unzip -o $fname -d bin/$arch
  rm $fname
done
