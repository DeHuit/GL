#! /bin/bash


RED=' \e[31m'
GREEN=' \e[32m'
NC=' \e[39m' # No Color

function do_test () {
  local prg="$1"
  local inputValues="$2"
  local expectedValues="$3"
  local n="$4"
  echo -e $inputValues > in.txt
  local answer=`java $1 in.txt`
  if [[ "$answer" == "$expectedValues" ]]; then
    echo -e "Test $n : ${GREEN}[OK]${NC}"
  else
    echo -e "Test $n : ${RED}[KO]${NC}"
  fi
}

function tests () {
  i=0
  while IFS='' read -r line || [[ -n "$line" ]]; do
    local inputValues=`echo "$line" | cut -d\; -f1`
    local expectedValues=`echo "$line" | cut -d\; -f2`
    i=`expr $i + 1`;
    do_test "$1" "$inputValues" "$expectedValues" $i
  done < $2
  echo "$i tests done"
  rm in.txt
}

if [[ $# != 2 ]]; then
  echo -e "Usage ./$0 programname testfile"
  exit 99
fi

tests $1 $2
